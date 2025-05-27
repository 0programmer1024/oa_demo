package chaoxing.oa_demo.vo.req;

import lombok.Data;

@Data
public class LoginReq {
    // TODO: 加非空判断
    public String username;
    public String password;
}
