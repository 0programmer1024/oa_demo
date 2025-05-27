package chaoxing.oa_demo.service.impl;

import chaoxing.oa_demo.enums.UserType;
import chaoxing.oa_demo.common.CustomException;
import chaoxing.oa_demo.entity.User;
import chaoxing.oa_demo.filter.UserContextHolder;
import chaoxing.oa_demo.mapper.UserMapper;
import chaoxing.oa_demo.service.UserService;
import chaoxing.oa_demo.utils.TokenUtils;
import chaoxing.oa_demo.vo.req.LoginReq;
import chaoxing.oa_demo.vo.req.User.InterviewerAddReq;
import chaoxing.oa_demo.vo.req.User.InterviewerUpdateReq;
import chaoxing.oa_demo.vo.resp.InterviewerVO;
import chaoxing.oa_demo.vo.resp.LoginVO;
import chaoxing.oa_demo.vo.resp.UserVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * 用户服务实现类
 */
@Service
@Slf4j
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User>
        implements UserService {


    @Override
    public IPage<UserVO> page(int pageNo, int pageSize) {
        return lambdaQuery()
                .eq(User::getType, UserType.Interviewer.getCode())
                .page(new Page<>(pageNo, pageSize))
                .convert(user -> BeanUtil.copyProperties(user, UserVO.class));
    }

    @Override
    public LoginVO login(LoginReq loginReq) {
        User user = this.lambdaQuery().eq(User::getUsername, loginReq.getUsername())
                .eq(User::getPassword, DigestUtils.md5DigestAsHex(loginReq.getPassword().getBytes())).one();
        if (ObjectUtil.isNotNull(user)) {
            String token = TokenUtils.getToken(user);
            return new LoginVO(token, BeanUtil.copyProperties(user, UserVO.class));
        } else {
            return new LoginVO(null, null);
        }
    }

    @Override
    public boolean add(InterviewerAddReq interviewerAddReq) {
        User user = BeanUtil.copyProperties(interviewerAddReq, User.class);
        if (ObjectUtil.isNotNull(lambdaQuery().eq(User::getUsername, interviewerAddReq.getUsername()).one())) {
            throw new CustomException(StrUtil.format("用户名[{}]已存在", user.getUsername()));
        }
        if (StrUtil.isNotBlank(user.getPassword())) {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        user.setType(UserType.Interviewer.getCode());
        return this.save(user);
    }

    @Override
    public List<InterviewerVO> interviewerList(String username) {
        List<User> interviewers = lambdaQuery()
                .eq(User::getType, UserType.Interviewer.getCode())
                .like(StrUtil.isNotBlank(username), User::getUsername, username).list();
        return BeanUtil.copyToList(interviewers, InterviewerVO.class);
    }

    @Override
    public boolean interviewerUpdate(InterviewerUpdateReq interviewerUpdateReq) {
        User oldUser = lambdaQuery().eq(User::getUsername, interviewerUpdateReq.getUsername())
                .ne(User::getId, interviewerUpdateReq.getId())
                .one();
        if(ObjectUtil.isNotNull(oldUser)){
            throw new CustomException(StrUtil.format("用户名[{}]已存在", oldUser.getUsername()));
        }
        if(UserType.Admin.getCode().equals(UserContextHolder.getUserToken().getType())
                && interviewerUpdateReq.getId().equals(UserContextHolder.getUserToken().getUserId())){
            throw new CustomException("面试官仅可修改自己的密码");
        }
        User user = BeanUtil.copyProperties(interviewerUpdateReq, User.class);
        if (StrUtil.isNotBlank(user.getPassword())) {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        return this.updateById(user);
    }
}




