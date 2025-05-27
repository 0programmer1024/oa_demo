package chaoxing.oa_demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName applicant
 */
@Data
@TableName(value ="applicant")
public class Applicant implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 用户名称
     */
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
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 版本
     */
    private Long version;

    /**
     * 是否删除标记
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 地址
     */
    private String address;

    /**
     * 评价
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String evaluation;

    /**
     * 面试官Id
     */
    private Long interviewerId;

    /**
     * 应聘时间
     */
    private LocalDateTime interviewTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}