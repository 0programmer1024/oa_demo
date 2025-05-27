package chaoxing.oa_demo.mapper;

import chaoxing.oa_demo.entity.Applicant;
import chaoxing.oa_demo.vo.resp.ApplicantVO;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author cxdev003922
* @description 针对表【applicant】的数据库操作Mapper
* @createDate 2025-05-23 09:58:38
* @Entity generator.domain.Applicant
*/
@Mapper
public interface ApplicantMapper extends MPJBaseMapper<Applicant> {
    @Select("""
            <script>
            SELECT
                a.id AS id,
                a.name AS name,
                a.phone AS phone,
                a.email AS email,
                latest_interview.interview_time AS interview_time,
                user.name AS interviewer_name,
                CASE
                    WHEN a.gender = TRUE THEN '男'
                    WHEN a.gender = FALSE THEN '女'
                    ELSE '-'
                END AS gender,
                a.birth_time AS birth_time,
                a.graduate_time AS graduate_time,
                a.resume_url AS resume_url
            FROM applicant a
            LEFT JOIN (
                SELECT applicant_id, interviewer_id, interview_time
                FROM interviewer_to_applicant ita1
                WHERE interview_time = (
                    SELECT MAX(interview_time)
                    FROM interviewer_to_applicant ita2
                    WHERE ita2.applicant_id = ita1.applicant_id
                )
            ) latest_interview ON a.id = latest_interview.applicant_id
            LEFT JOIN user ON latest_interview.interviewer_id = user.id
            <where>
                <if test='name != null and name != ""'>
                    AND a.name LIKE CONCAT('%', #{name}, '%')
                </if>
                <if test='email != null and email != ""'>
                    AND a.email LIKE CONCAT('%', #{email}, '%')
                </if>
                <if test='startTime != null'>
                    AND latest_interview.interview_time &gt;= #{startTime}
                </if>
                <if test='endTime != null'>
                    AND latest_interview.interview_time &lt;= #{endTime}
                </if>
                <if test='interviewerName != null and interviewerName != ""'>
                    AND user.name LIKE CONCAT('%', #{interviewerName}, '%')
                </if>
                <if test='interviewerName != null'>
                    AND latest_interview.interviewer_id = #{interviewerId})
                </if>
            </where>
            ORDER BY a.create_time DESC
            LIMIT ${(pageNo - 1) * pageSize}, ${pageSize}
            </script>
    """)
    List<ApplicantVO> getQueryPage(@Param("pageNo") int pageNo,
                                   @Param("pageSize") int pageSize,
                                   @Param("name") String name,
                                   @Param("email") String email,
                                   @Param("startTime") LocalDateTime startTime,
                                   @Param("endTime") LocalDateTime endTime,
                                   @Param("interviewerName") String interviewerName,
                                   @Param("interviewerId") Long interviewerId);
}




