package chaoxing.oa_demo.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
public class CustomFilter extends OncePerRequestFilter {
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        // 自定义过滤逻辑，例如记录请求日志
        System.out.println("Request URI: " + request.getRequestURI());
        
        // 继续执行过滤链
        filterChain.doFilter(request, response);
    }
}