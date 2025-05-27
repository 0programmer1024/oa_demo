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
    /**
     * 应聘人分页查询
     *
     * @param page                 页码
     * @param pageSize             每页大小
     * @param name                 应聘人姓名
     * @param email                应聘人邮箱
     * @param interviewerTimeStart 应聘时间筛选（开始）
     * @param interviewerTimeEnd   应聘时间筛选（结束）
     * @param interviewerName      面试人姓名
     * @param interviewerFlag      是否是面试人请求
     * @return 应聘人分页查询结果
     */
    Page<ApplicantVO> page(int page, int pageSize, String name, String email, LocalDateTime interviewerTimeStart, LocalDateTime interviewerTimeEnd, String interviewerName, Boolean interviewerFlag);

    /**
     * 应聘人添加
     *
     * @param applicantAddReq 应聘人添加VO
     */
    void add(ApplicantAddReq applicantAddReq);
}
