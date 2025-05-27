package chaoxing.oa_demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型枚举
 */
@Getter
@AllArgsConstructor
public enum UserType {
    Admin(10, "管理员"),
    Interviewer(20, "面试官");
    private final Integer code;
    private final String description;

    public static UserType fromCode(Integer code) {
        for (UserType type : UserType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
