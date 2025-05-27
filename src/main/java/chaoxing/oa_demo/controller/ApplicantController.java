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

@RestController
@RequestMapping("/api/applicant")
@Slf4j
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;

    /**
     * 应聘者信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
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
                                     ){
        return R.success(applicantService.page(page, pageSize, name, email, interviewerTimeStart, interviewerTimeEnd, interviewerName, interviewerFlag));
    }

    /**
     * 新增应聘者
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody ApplicantAddReq applicantAddReq){
        applicantService.add(applicantAddReq);
        return R.success("新增应聘者成功");
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id){
        applicantService.removeById(id);
        return R.success("删除成功");
    }

    /**
     * 根据id修改用户信息
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody ApplicantUpdateReq applicantUpdateReq){
        applicantService.updateById(BeanUtil.copyProperties(applicantUpdateReq, Applicant.class));
        return R.success("应聘者信息修改成功");
    }
}
