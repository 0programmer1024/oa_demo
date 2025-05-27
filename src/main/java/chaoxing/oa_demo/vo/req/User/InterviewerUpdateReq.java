package chaoxing.oa_demo.vo.req.User;

import lombok.Data;

@Data
public class InterviewerUpdateReq extends InterviewerAddReq{
    /**
     * 用户id
     */
    private Long id;
}
