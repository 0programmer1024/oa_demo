package chaoxing.oa_demo.controller;

import chaoxing.oa_demo.common.R;
import chaoxing.oa_demo.service.UserService;
import chaoxing.oa_demo.vo.req.LoginReq;
import chaoxing.oa_demo.vo.req.User.InterviewerAddReq;
import chaoxing.oa_demo.vo.req.User.InterviewerUpdateReq;
import chaoxing.oa_demo.vo.resp.InterviewerVO;
import chaoxing.oa_demo.vo.resp.LoginVO;
import chaoxing.oa_demo.vo.resp.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息controller
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    /**
     * 面试官分页查询
     *
     * @param pageNo   页码
     * @param pageSize 每页大小
     * @return 用户信息
     */
    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/interviewer/page")
    public R<IPage<UserVO>> page(@RequestParam int pageNo,
                                 @RequestParam int pageSize) {
        return R.success(userService.page(pageNo, pageSize));
    }

    /**
     * 全量查询面试官
     *
     * @param username 面试官姓名
     * @return 面试官列表
     */
    @GetMapping("/interviewer/all")
    public R<List<InterviewerVO>> queryAllInterviewers(@RequestParam(required = false) String username) {
        return R.success(userService.interviewerList(username));
    }

    /**
     * 用户登录
     *
     * @param loginReq 登录请求体
     * @return 登录信息
     */
    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody LoginReq loginReq) {
        return R.success(userService.login(loginReq));
    }

    /**
     * 面试官添加
     *
     * @param interviewerAddReq 面试官添加VO
     * @return 面试官添加响应
     */
    @PostMapping("/interviewer")
    public R<String> save(@RequestBody InterviewerAddReq interviewerAddReq) {
        userService.add(interviewerAddReq);
        return R.success("新增用户成功");
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id) {
        userService.removeById(id);
        return R.success("删除成功");
    }

    /**
     * 更新面试官信息
     *
     * @param interviewerUpdateReq 面试官更新请求体
     * @return 是否更新成功
     */
    @PutMapping("/interviewer")
    public R<Boolean> update(@RequestBody InterviewerUpdateReq interviewerUpdateReq) {
        return R.success(userService.interviewerUpdate(interviewerUpdateReq));
    }
}
