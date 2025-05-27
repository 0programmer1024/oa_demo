package chaoxing.oa_demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 请求方法和uri
 */
@Data
@AllArgsConstructor
public class RequestMethod {
    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求uri
     */
    private String uri;
}
