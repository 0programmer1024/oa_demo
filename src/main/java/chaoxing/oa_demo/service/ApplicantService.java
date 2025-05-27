package chaoxing.oa_demo.service;

import chaoxing.oa_demo.entity.Applicant;
import chaoxing.oa_demo.vo.req.Applicant.ApplicantAddReq;
import chaoxing.oa_demo.vo.resp.ApplicantVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseService;

import java.time.LocalDateTime;

/**
* @author cxdev003922
* @description 针对表【applicant】的数据库操作Service
* @createDate 2025-05-23 09:58:38
*/
public interface ApplicantService extends MPJBaseService<Applicant> {

    Page<ApplicantVO> page(int page, int pageSize, String name, String email, LocalDateTime applyStartTime, LocalDateTime applyEndTime, String interviewerName, Boolean interviewerFlag);


    void add(ApplicantAddReq applicantAddReq);
}
