package chaoxing.oa_demo.vo.req.User;

import lombok.Data;

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
