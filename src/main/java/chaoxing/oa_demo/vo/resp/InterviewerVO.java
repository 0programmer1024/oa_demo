package chaoxing.oa_demo.vo.resp;

import lombok.Data;

/**
 * 面试官vo(用于下拉框)
 */
@Data
public class InterviewerVO {
    /**
     * 面试官用户id
     */
    private Long id;

    /**
     * 面试官姓名
     */
    private String username;
}
