//package chaoxing.oa_demo.entity;
//
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import lombok.Data;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// *
// * @TableName interviewer_to_applicant
// */
//@Data
//@TableName(value ="interviewer_to_applicant")
//public class InterviewerToApplicant implements Serializable {
//    /**
//     * 主键
//     */
//    @TableId
//    private Long id;
//
//    /**
//     * 应聘人ID
//     */
//    private Long applicantId;
//
//    /**
//     * 面试人ID
//     */
//    private Long interviewerId;
//
//    /**
//     * 应聘时间
//     */
//    // TODO: 应聘时间应取面试的时间，因简易系统无录入的地方，默认取面试评价录入时间
//    private Date interview_time;
//
//    @TableField(exist = false)
//    private static final long serialVersionUID = 1L;
//}