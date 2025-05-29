package chaoxing.oa_demo.service.impl;

import chaoxing.oa_demo.common.CustomException;
import chaoxing.oa_demo.common.UserToken;
import chaoxing.oa_demo.consts.RedisKeys;
import chaoxing.oa_demo.entity.User;
import chaoxing.oa_demo.enums.UserType;
import chaoxing.oa_demo.mapper.UserMapper;
import chaoxing.oa_demo.service.UserService;
import chaoxing.oa_demo.utils.JwtUtil;
import chaoxing.oa_demo.vo.req.LoginReq;
import chaoxing.oa_demo.vo.req.User.InterviewerAddReq;
import chaoxing.oa_demo.vo.req.User.InterviewerUpdateReq;
import chaoxing.oa_demo.vo.resp.InterviewerVO;
import chaoxing.oa_demo.vo.resp.LoginVO;
import chaoxing.oa_demo.vo.resp.UserVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 用户服务实现类
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User>
        implements UserService {
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RedisService redisService;


    @Override
    public IPage<UserVO> page(int pageNo, int pageSize) {
        return lambdaQuery()
                .eq(User::getType, UserType.Interviewer.getCode())
                .page(new Page<>(pageNo, pageSize))
                .convert(user -> BeanUtil.copyProperties(user, UserVO.class));
    }

    @Override
    public LoginVO login(LoginReq loginReq) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){
            throw new CustomException("用户名或密码错误");
        }
        User user = ((UserDetailsImpl) authenticate.getPrincipal()).getUser();
        String token = JwtUtil.createJWT(JSONUtil.toJsonStr(new UserToken(user.getId(), user.getType())));
        redisService.setValue(RedisKeys.TOKEN + user.getId(), token);
        return new LoginVO(token, BeanUtil.copyProperties(user, UserVO.class));
    }

    @Override
    public boolean add(InterviewerAddReq interviewerAddReq) {
        User user = BeanUtil.copyProperties(interviewerAddReq, User.class);
        if (ObjectUtil.isNotNull(lambdaQuery().eq(User::getUsername, interviewerAddReq.getUsername()).one())) {
            throw new CustomException(StrUtil.format("用户名[{}]已存在", user.getUsername()));
        }
        if (StrUtil.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserToken userToken = (UserToken) authenticationToken.getPrincipal();
        User oldUser = lambdaQuery().eq(User::getUsername, interviewerUpdateReq.getUsername())
                .ne(User::getId, interviewerUpdateReq.getId())
                .one();
        if(ObjectUtil.isNotNull(oldUser)){
            throw new CustomException(StrUtil.format("用户名[{}]已存在", oldUser.getUsername()));
        }
        if(UserType.Admin.getCode().equals(userToken.getType())
                && interviewerUpdateReq.getId().equals(userToken.getId())){
            throw new CustomException("面试官仅可修改自己的密码");
        }
        User newUser = BeanUtil.copyProperties(interviewerUpdateReq, User.class);
        if (StrUtil.isNotBlank(newUser.getPassword())) {
            newUser.setPassword(passwordEncoder.encode(interviewerUpdateReq.getPassword()));
        }
        redisService.deleteKey(RedisKeys.TOKEN + userToken.getId());
        return this.updateById(newUser);
    }
}




