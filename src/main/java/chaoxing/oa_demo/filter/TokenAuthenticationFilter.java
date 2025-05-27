package chaoxing.oa_demo.filter;

import chaoxing.oa_demo.common.CustomException;
import chaoxing.oa_demo.common.R;
import chaoxing.oa_demo.common.RequestMethod;
import chaoxing.oa_demo.common.UserToken;
import chaoxing.oa_demo.consts.TokenPayloads;
import chaoxing.oa_demo.enums.UserType;
import chaoxing.oa_demo.utils.TokenUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class TokenAuthenticationFilter implements Filter {
    @Autowired
    @Qualifier("userTypeAuthMap")
    private Map<Integer, List<RequestMethod>> userTypeAuthMap;

    @Autowired
    @Qualifier("visitorAuthList")
    private List<RequestMethod> visitorAuthList;

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
            String method = request.getMethod();
            String uri = request.getRequestURI();
            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                filterChain.doFilter(request, servletResponse);
                return;
            }
            String token = request.getHeader("token");
            if (StrUtil.isBlank(token) || "undefined".equals(token)) {
                authApi(new RequestMethod(method, uri), null);
                filterChain.doFilter(request, servletResponse);
                return;
            }
            boolean isValid = JWTUtil.verify(token, TokenUtils.key);
            if (!isValid) {
                throw new CustomException("JWT令牌验证不通过");
            }
            JWTValidator.of(token).validateDate();
            JWT jwt = JWTUtil.parseToken(token);
            String userId = jwt.getPayload(TokenPayloads.X_USER_ID).toString();
            String userType = jwt.getPayload(TokenPayloads.X_USER_TYPE).toString();
            long userIdLong = 0L;
            int userTypeInt = 0;
            try {
                userIdLong = Long.parseLong(userId);
                userTypeInt = Integer.parseInt(userType);
            } catch (NumberFormatException e) {
                log.warn("用户信息不合法,userId: {}, userType : {}", userId, userType);
            }
            if(!authApi(new RequestMethod(method, uri), userTypeInt)){
                setReturn(request, (HttpServletResponse) servletResponse, "鉴权未通过");
                return ;
            }
            UserContextHolder.removeUserToken();
            UserContextHolder.setUserToken(new UserToken(userIdLong, userTypeInt));
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContextHolder.removeUserToken();
        }


    }

    boolean authApi(RequestMethod method, Integer userType){
        //TODO: 简易鉴权过滤器
        //白名单
        RequestMethod Whitelist = new RequestMethod("POST", "/api/user/login");
        List<RequestMethod> requestMethods = new ArrayList<>();
        requestMethods.add(Whitelist);
        if(userType == null){
            requestMethods.addAll(visitorAuthList);
        }
        if(Objects.equals(userType, UserType.Admin.getCode())){
            //管理员有所有权限
            return true;
        }
        if(CollUtil.isNotEmpty(userTypeAuthMap.get(userType))){
            requestMethods.addAll(userTypeAuthMap.get(userType));
        }
        return requestMethods.contains(method);
    }

    /**
     * 设置鉴权未通过响应
     *
     * @param request      HTTP请求
     * @param httpResponse HTTP响应
     * @param msg          错误信息
     * @throws IOException IO异常
     */
    private static void setReturn(HttpServletRequest request, HttpServletResponse httpResponse, String msg) throws IOException {
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(R.error(msg));
        httpResponse.getWriter().print(json);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器已经死亡");
        Filter.super.destroy();
    }
}
