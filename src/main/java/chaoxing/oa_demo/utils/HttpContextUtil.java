package chaoxing.oa_demo.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Http上下文工具
 */
public class HttpContextUtil {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getOrigin() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }
}
