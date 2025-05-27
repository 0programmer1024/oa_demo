package chaoxing.oa_demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {

    /**
     * 当前页码
     */
    private Long pageNo = 1L;

    /**
     * 每页展示记录数
     */
    private Long pageSize = 20L;
}
