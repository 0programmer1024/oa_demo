package chaoxing.oa_demo.controller;

import chaoxing.oa_demo.common.R;
import chaoxing.oa_demo.entity.Applicant;
import chaoxing.oa_demo.service.ApplicantService;
import chaoxing.oa_demo.vo.req.Applicant.ApplicantAddReq;
import chaoxing.oa_demo.vo.req.Applicant.ApplicantUpdateReq;
import chaoxing.oa_demo.vo.resp.ApplicantVO;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 应聘人controller
 */
@RestController
@RequestMapping("/api/applicant")
@Slf4j
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;

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
    @GetMapping("/page")
    public R<Page<ApplicantVO>> page(@RequestParam int page,
                                     @RequestParam int pageSize,
                                     String name,
                                     String email,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime interviewerTimeStart,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime interviewerTimeEnd,
                                     String interviewerName,
                                     @RequestParam(defaultValue = "false") Boolean interviewerFlag
    ) {
        return R.success(applicantService.page(page, pageSize, name, email, interviewerTimeStart, interviewerTimeEnd, interviewerName, interviewerFlag));
    }

    /**
     * 应聘人保存
     *
     * @param applicantAddReq 应聘人添加VO
     * @return 应聘人保存结果
     */
    @PostMapping
    public R<String> save(@RequestBody ApplicantAddReq applicantAddReq) {
        applicantService.add(applicantAddReq);
        return R.success("新增应聘者成功");
    }

    /**
     * 应聘人删除
     *
     * @param id 应聘人id
     * @return 应聘人删除结果
     */
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id) {
        applicantService.removeById(id);
        return R.success("删除成功");
    }

    /**
     * 应聘人更新
     *
     * @param applicantUpdateReq 应聘人更新请求
     * @return 应聘人更新结果
     */
    @PutMapping
    public R<String> update(@RequestBody ApplicantUpdateReq applicantUpdateReq) {
        applicantService.updateById(BeanUtil.copyProperties(applicantUpdateReq, Applicant.class));
        return R.success("应聘者信息修改成功");
    }
}
