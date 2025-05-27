package chaoxing.oa_demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    Admin(10, "管理员"),
    Interviewer(20, "面试官");
    private final Integer code;
    private final String description;
}
