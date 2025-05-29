package chaoxing.oa_demo.service.impl;

import chaoxing.oa_demo.common.UserToken;
import chaoxing.oa_demo.entity.Applicant;
import chaoxing.oa_demo.entity.User;
import chaoxing.oa_demo.enums.UserType;
import chaoxing.oa_demo.mapper.ApplicantMapper;
import chaoxing.oa_demo.service.ApplicantService;
import chaoxing.oa_demo.vo.req.Applicant.ApplicantAddReq;
import chaoxing.oa_demo.vo.resp.ApplicantVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 应聘者服务实现类
 */
@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl extends MPJBaseServiceImpl<ApplicantMapper, Applicant>
        implements ApplicantService {

    @Override
    public Page<ApplicantVO> page(int page, int pageSize, String name, String email,
                                  LocalDateTime interviewStartTime, LocalDateTime interviewEndTime,
                                  String interviewerName, Boolean interviewerFlag) {
        MPJLambdaWrapper<Applicant> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.leftJoin(User.class, on -> on.eq(User::getId, Applicant::getInterviewerId)
                .eq(User::getType, UserType.Interviewer.getCode())).disableSubLogicDel();
        if (interviewerFlag) {
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            UserToken userToken = (UserToken) authenticationToken.getPrincipal();
            Long interviewerId = userToken.getId();
            queryWrapper.eq(Applicant::getInterviewerId, interviewerId);
        } else {
            queryWrapper.like(StrUtil.isNotBlank(name), Applicant::getName, name);
            queryWrapper.like(StrUtil.isNotBlank(email), Applicant::getEmail, email);
            queryWrapper.like(StrUtil.isNotBlank(interviewerName), User::getUsername, interviewerName);
            queryWrapper.ge(ObjectUtil.isNotEmpty(interviewStartTime), Applicant::getInterviewTime, interviewStartTime);
            queryWrapper.le(ObjectUtil.isNotEmpty(interviewEndTime), Applicant::getInterviewTime, interviewEndTime);
        }
        queryWrapper.selectAll(Applicant.class);
        queryWrapper.selectAs(User::getId, ApplicantVO::getInterviewerId);
        queryWrapper.selectAs(User::getUsername, ApplicantVO::getInterviewerName);
        queryWrapper.orderByDesc(Applicant::getCreateTime);
        return baseMapper.selectJoinPage(new Page<>(page, pageSize), ApplicantVO.class, queryWrapper);
    }

    @Override
    public void add(ApplicantAddReq applicantAddReq) {
        this.save(BeanUtil.copyProperties(applicantAddReq, Applicant.class));
    }

}




