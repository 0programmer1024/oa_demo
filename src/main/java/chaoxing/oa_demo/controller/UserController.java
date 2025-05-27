package chaoxing.oa_demo.controller;

import chaoxing.oa_demo.common.R;
import chaoxing.oa_demo.entity.User;
import chaoxing.oa_demo.service.UserService;
import chaoxing.oa_demo.vo.req.LoginReq;
import chaoxing.oa_demo.vo.req.User.InterviewerAddReq;
import chaoxing.oa_demo.vo.req.User.InterviewerUpdateReq;
import chaoxing.oa_demo.vo.resp.InterviewerVO;
import chaoxing.oa_demo.vo.resp.LoginVO;
import chaoxing.oa_demo.vo.resp.UserVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
//@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    /**
     * 面试官分页查询
     * @return
     */
    @GetMapping("/interviewer/page")
    public R<IPage<UserVO>> page(@RequestParam int pageNo,
                                 @RequestParam int pageSize){
        return R.success(userService.page(pageNo, pageSize));
    }

    /**
     * 面试官全量查询
     * @return
     */
    @GetMapping("/interviewer/all")
    public R<List<InterviewerVO>> queryAllInterviewers(@RequestParam(required = false) String name){
        return R.success(userService.interviewerList(name));
    }


    /**
     * 用户登录
     * @return
     */
    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody LoginReq loginReq){
        LoginVO loginVO = userService.login(loginReq);
        if(StrUtil.isBlank(loginVO.getToken())){
            return R.error("登陆失败");
        }
        return R.success(loginVO);
    }
//
//    @GetMapping("/checkuserlogininfo")
//    @ResponseBody
//    public R<UserToken> checkToken(@RequestHeader("token") String token) {
//        return R.success(TokenUtils.decodeToken(token));
//    }



     /**
     * 新增面试官
     * @return
     */
    @PostMapping("/interviewer")
    public R<String> save(@RequestBody InterviewerAddReq userAddReq){
        userService.add(userAddReq);
        return R.success("新增用户成功");
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id){
        userService.removeById(id);
        return R.success("删除成功");
    }

     /**
     * 根据id修改用户信息
     * @return
     */
    @PutMapping("/interviewer")
    public R<String> update(@RequestBody InterviewerUpdateReq interviewerUpdateReq){
        User user = BeanUtil.copyProperties(interviewerUpdateReq, User.class);
        if(StrUtil.isNotBlank(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        userService.updateById(user);
        return R.success("用户信息修改成功");
    }
}
