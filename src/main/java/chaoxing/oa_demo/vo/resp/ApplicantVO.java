package chaoxing.oa_demo.vo.resp;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 应聘人VO
 */
@Data
public class ApplicantVO {
    /**
     * ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮件
     */
    private String email;

    /**
     * 应聘时间
     */
    private LocalDateTime interviewTime;

    /**
     * 面试人Id
     */
    private String interviewerId;

    /**
     * 面试人名称
     */
    private String interviewerName;

    /**
     * 性别
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
     * 简历Url
     */
    private String resumePath;

    /**
     * 评价
     */
    private String evaluation;

    /**
     * 地址
     */
    private String address;
//
//    /**
//     * 创建时间
//     */
//    private LocalDateTime createTime;
//
//    /**
//     * 创建人
//     */
//    private Long createBy;
//
//    /**
//     * 更新时间
//     */
//    private LocalDateTime updateTime;
//
//    /**
//     * 更新人
//     */
//    private Long updateBy;
}
