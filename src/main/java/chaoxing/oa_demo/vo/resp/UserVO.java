package chaoxing.oa_demo.vo.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class UserVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 类型（10：管理员, 20:面试官）
     */
    private Integer type;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;
}
