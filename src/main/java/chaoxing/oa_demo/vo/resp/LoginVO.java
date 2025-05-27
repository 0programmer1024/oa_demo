package chaoxing.oa_demo.vo.resp;

import chaoxing.oa_demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {

    private String token;

    private UserVO user;
}
