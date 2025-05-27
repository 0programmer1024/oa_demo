package chaoxing.oa_demo.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登陆成功响应信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    /**
     * Token
     */
    private String token;
    /**
     * 用户信息
     */
    private UserVO user;
}
