package chaoxing.oa_demo.vo.req.User;

import lombok.Data;

/**
 * 面试官添加请求
 */
@Data
public class InterviewerAddReq {
    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;
}
