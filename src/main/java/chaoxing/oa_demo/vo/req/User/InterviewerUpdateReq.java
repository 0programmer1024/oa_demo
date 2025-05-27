package chaoxing.oa_demo.vo.req.User;

import lombok.Data;

/**
 * 面试官更新请求
 */
@Data
public class InterviewerUpdateReq extends InterviewerAddReq {
    /**
     * 用户id
     */
    private Long id;
}
