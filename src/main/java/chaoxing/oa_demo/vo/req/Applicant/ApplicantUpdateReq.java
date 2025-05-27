package chaoxing.oa_demo.vo.req.Applicant;

import lombok.Data;

@Data
public class ApplicantUpdateReq extends ApplicantAddReq{
    /**
     * 用户id
     */
    private Long id;
}
