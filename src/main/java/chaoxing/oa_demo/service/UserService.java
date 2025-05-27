package chaoxing.oa_demo.service;

import chaoxing.oa_demo.entity.User;
import chaoxing.oa_demo.vo.req.LoginReq;
import chaoxing.oa_demo.vo.req.User.InterviewerAddReq;
import chaoxing.oa_demo.vo.req.User.InterviewerUpdateReq;
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
    /**
     * 用户分页查询
     *
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return 用户分页查询结果
     */
    IPage<UserVO> page(int pageNo, int pageSize);

    /**
     * 登录
     *
     * @param loginReq 登录请求体
     * @return 登录信息
     */
    LoginVO login(LoginReq loginReq);

    /**
     * 面试官添加
     *
     * @param interviewerAddReq 面试官添加VO
     * @return 面试官添加响应
     */
    boolean add(InterviewerAddReq interviewerAddReq);

    /**
     * 全量查询面试官
     *
     * @param username 面试官姓名
     * @return 面试官列表
     */
    List<InterviewerVO> interviewerList(String username);

    /**
     * 更新用户
     *
     * @param interviewerUpdateReq 用户信息
     * @return 更新结果
     */
    boolean interviewerUpdate(InterviewerUpdateReq interviewerUpdateReq);
}
