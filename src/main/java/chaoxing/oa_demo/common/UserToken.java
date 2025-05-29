package chaoxing.oa_demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 用户token(可用UserContextHolder获取上下文token)
 */
public class UserToken {
    /**
     * 用户Id
     */
    private Long id;

    /**
     * 类型（10：管理员, 20:面试官）
     */
    private Integer type;
}
