package chaoxing.oa_demo.filter;

import chaoxing.oa_demo.common.UserToken;
import chaoxing.oa_demo.consts.HttpHeaders;
import chaoxing.oa_demo.utils.HttpContextUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class TokenAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("-----------------过滤器初始化----------------");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {

            //校验用户登录状态
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                filterChain.doFilter(request, servletResponse);
                return;
            }
//            HttpServletResponse response = (HttpServletResponse) servletResponse;
////            String token1 = HttpContextUtil.getToken();
//            System.out.println("执行过滤器");
//            //取出token
            String token = request.getHeader("token");
            if(StrUtil.isBlank(token) || "undefined".equals(token)) {
                filterChain.doFilter(request, servletResponse);
                return;
            }
            String origin = request.getHeader("origin");
            String referer = request.getHeader("referer");
            //第一次登录没有token，给null会报错，所以我们判断一下token是否为空，为空给一个空串
            //三元运算
            token = token == null ? "" : token;
            boolean isValid = JWTUtil.verify(token, "secret-demo".getBytes());
            if (!isValid) {
                throw new RuntimeException("Invalid JWT signature!");
            }

//        // 2. 验证 Token 是否过期（如果有 exp 字段）
//        JWTValidator.of(token).validateDate();


            JWT jwt = JWTUtil.parseToken(token);
            String userId = jwt.getPayload(HttpHeaders.X_USER_ID).toString();
            long userIdLong = 0L;
            try {
                userIdLong = Long.parseLong(userId);
            } catch (NumberFormatException e) {
                log.warn("USER ID INVALID", userId);
            }
            UserContextHolder.removeUserToken();
            UserContextHolder.setUserToken(new UserToken(userIdLong));
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            UserContextHolder.removeUserToken();
        }


    }

    @Override
    public void destroy() {
        System.out.println("过滤器已经死亡！");
        Filter.super.destroy();
    }
}
