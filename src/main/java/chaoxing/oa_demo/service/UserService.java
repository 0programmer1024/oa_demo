package chaoxing.oa_demo.service;

import chaoxing.oa_demo.entity.User;
import chaoxing.oa_demo.vo.req.LoginReq;
import chaoxing.oa_demo.vo.req.User.InterviewerAddReq;
import chaoxing.oa_demo.vo.resp.InterviewerVO;
import chaoxing.oa_demo.vo.resp.LoginVO;
import chaoxing.oa_demo.vo.resp.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
* @author LFH
* @description 针对表【user】的数据库操作Service
* @createDate 2025-05-22 14:07:29
*/
public interface UserService extends MPJBaseService<User> {

    IPage<UserVO> page(int pageNo, int pageSize);

    LoginVO login(LoginReq loginReq);

    boolean add(InterviewerAddReq userAddReq);

    List<InterviewerVO> interviewerList(String name);
}
