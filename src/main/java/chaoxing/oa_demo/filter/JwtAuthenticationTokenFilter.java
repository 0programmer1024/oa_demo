package chaoxing.oa_demo.filter;

import chaoxing.oa_demo.common.CustomException;
import chaoxing.oa_demo.common.UserToken;
import chaoxing.oa_demo.consts.RedisKeys;
import chaoxing.oa_demo.enums.UserType;
import chaoxing.oa_demo.mapper.UserMapper;
import chaoxing.oa_demo.service.impl.RedisService;
import chaoxing.oa_demo.utils.JwtUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final UserMapper userMapper;

    private final RedisService redisService;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")
                || "/api/user/login".equals(request.getRequestURI()) || "undefined".equalsIgnoreCase(token)) {
            filterChain.doFilter(request, response);
            return;
        }
 
        token = token.substring(7);
 
        UserToken userToken;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            String userTokenJson = claims.getSubject();
            userToken = JSONUtil.toBean(userTokenJson, UserToken.class);
        } catch (Exception e) {
            throw new CustomException(e.toString());
        }
        if(!token.equals(redisService.getValue(RedisKeys.TOKEN + userToken.getId()))){
            throw new CustomException("Token失效");
        }


//        UserDetailsImpl loginUser = new UserDetailsImpl(BeanUtil.copyProperties(userToken,User.class));
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userToken, null, List.of(new SimpleGrantedAuthority(
                        StrUtil.format("ROLE_{}", Objects.requireNonNull(UserType.fromCode(userToken.getType())).toString()))));
        // 如果是有效的jwt，那么设置该用户为认证后的用户
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
 
        filterChain.doFilter(request, response);
    }
}