package chaoxing.oa_demo.vo.resp;

import lombok.Data;

/**
 * 用户信息
 */
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
     * 类型（10：管理员, 20:面试官）
     */
    private Integer type;
}
