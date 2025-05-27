package chaoxing.oa_demo.service.impl;

import chaoxing.oa_demo.UserType;
import chaoxing.oa_demo.common.UserToken;
import chaoxing.oa_demo.entity.Applicant;
import chaoxing.oa_demo.entity.User;
import chaoxing.oa_demo.filter.UserContextHolder;
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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @author cxdev003922
* @description 针对表【applicant】的数据库操作Service实现
* @createDate 2025-05-23 09:58:38
*/
@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl extends MPJBaseServiceImpl<ApplicantMapper, Applicant>
    implements ApplicantService {


    @Override
    public Page<ApplicantVO> page(int page, int pageSize, String name, String email,
                                  LocalDateTime interviewStartTime, LocalDateTime interviewEndTime,
                                  String interviewerName, Boolean interviewerFlag) {
        UserToken token = UserContextHolder.getUserToken();
        MPJLambdaWrapper<Applicant> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.leftJoin(User.class,  on -> on.eq(User::getId, Applicant::getInterviewerId)
                .eq(User::getType, UserType.Interviewer.getCode())).disableSubLogicDel();
        if(interviewerFlag){
            // TODO : 从Token中获取
            Long interviewerId = UserContextHolder.getUserToken().getUserId();
            queryWrapper.eq(Applicant::getInterviewerId, interviewerId);
        }else{
            queryWrapper.like(StrUtil.isNotBlank(name), Applicant::getName, name);
            queryWrapper.like(StrUtil.isNotBlank(email), Applicant::getEmail, email);
            queryWrapper.like(StrUtil.isNotBlank(interviewerName), User::getName, interviewerName);
            queryWrapper.ge(ObjectUtil.isNotEmpty(interviewStartTime), Applicant::getInterviewTime, interviewStartTime);
            queryWrapper.le(ObjectUtil.isNotEmpty(interviewEndTime), Applicant::getInterviewTime, interviewEndTime);
        }
        queryWrapper.selectAll(Applicant.class);
        queryWrapper.selectAs(User::getId, ApplicantVO::getInterviewerId);
        queryWrapper.selectAs(User::getName, ApplicantVO::getInterviewerName);
        Page<ApplicantVO> applicantVOPage = baseMapper.selectJoinPage(new Page<>(page, pageSize), ApplicantVO.class, queryWrapper);
        applicantVOPage.getRecords().forEach(applicantVO -> {
            Boolean gender = applicantVO.getGender();
            if(ObjectUtil.isNull(gender)){
                applicantVO.setGenderName("未知");
            }else{
                applicantVO.setGenderName(gender ? "男" : "女");
            }
        });
        return applicantVOPage;
    }

    @Override
    public void add(ApplicantAddReq applicantAddReq) {
        Applicant applicant = BeanUtil.copyProperties(applicantAddReq, Applicant.class);
//        applicant.setUpdateBy(UserContextHolder.getUserToken().getUserId());
//        applicant.setCreateBy(UserContextHolder.getUserToken().getUserId());
        this.save(applicant);
    }


//    private final ApplicantMapper applicantMapper;
//    @Override
//    public Page<ApplicantVO> page(int page, int pageSize, String name, String email,
//                                  LocalDateTime applyStartTime, LocalDateTime applyEndTime,
//                                  String interviewerName) {
//        Page<ApplicantVO> voPage = new Page<>(page, pageSize);
//        // TODO : 从Token中获取
//        Long interviewerId = null;
//        List<ApplicantVO> applicantVOs = baseMapper.getQueryPage(page, pageSize, name, email, applyStartTime, applyEndTime, interviewerName, interviewerId);
//        voPage.setRecords(applicantVOs);
//        voPage.setSize(applicantVOs.size());
//        return voPage;
//    }
}




