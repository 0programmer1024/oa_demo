package chaoxing.oa_demo.vo.req.Applicant;

import lombok.Data;

/**
 * 应聘者更新请求
 */
@Data
public class ApplicantUpdateReq extends ApplicantAddReq {
    /**
     * 用户id
     */
    private Long id;
}
