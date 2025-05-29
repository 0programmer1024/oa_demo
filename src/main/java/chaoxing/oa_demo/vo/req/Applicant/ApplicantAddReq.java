package chaoxing.oa_demo.vo.req.Applicant;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 应聘者添加请求
 */
@Data
public class ApplicantAddReq {
    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称不能为空")
    private String name;

    /**
     * 邮件
     */
    private String email;

    /**
     * 性别(TRUE-男, FALSE-女, NULL-未知)
     */
    private Boolean gender;

    /**
     * 出生日期
     */
    private LocalDateTime birthTime;

    /**
     * 毕业日期
     */
    private LocalDateTime graduateTime;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 简历路径
     */
    private String resumePath;

    /**
     * 地址
     */
    private String address;

    /**
     * 面试官Id
     */
    private Long interviewerId;

    /**
     * 应聘时间
     */
    private LocalDateTime interviewTime;

    /**
     * 评价
     */
    private String evaluation;
}
