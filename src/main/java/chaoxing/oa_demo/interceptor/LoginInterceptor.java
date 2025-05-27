//package chaoxing.oa_demo.interceptor;
//
//import chaoxing.oa_demo.common.R;
//import chaoxing.oa_demo.utils.HttpContextUtil;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        // 放行 OPTIONS 请求（跨域预检请求）
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            return true;
//        }
//        //从header中获取token
//        String token = request.getHeader("token");
//        //如果token为空
//        if (StringUtils.isBlank(token)) {
//            setReturn(response, "用户未登录，请先登录");
//            return false;
//        }
//        return true;
//    }
//
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
//        //从header中获取token
//        String token = request.getHeader("token");
//        //如果token为空
//        if (StringUtils.isBlank(token)) {
//            setReturn(response, "用户未登录，请先登录");
//        }
//    }
//
//    /**
//     * 设置鉴权未通过响应
//     *
//     * @param httpResponse HTTP响应
//     * @param msg          错误信息
//     * @throws IOException IO异常
//     */
//    private static void setReturn(HttpServletResponse httpResponse, String msg) throws IOException {
//        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
//        httpResponse.setCharacterEncoding("UTF-8");
//        httpResponse.setContentType("application/json;charset=utf-8");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(R.error(msg));
//        httpResponse.getWriter().print(json);
//    }
//
//}
