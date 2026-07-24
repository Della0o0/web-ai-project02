package top.annieholo.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.annieholo.utils.jwt.JwtUtil;

import java.io.IOException;

@Slf4j
// @WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1、获取请求路径
        // String remoteAddr = servletRequest.getRemoteAddr();
        // log.info("remoteAddr: {}", remoteAddr);
        String requestURI = request.getRequestURI();
        log.info("-------requestURI: {}", requestURI);
        // 2、判断是否是登陆请求 /login 放行
        if(requestURI.contains("/login")){
            filterChain.doFilter(request, response);
            return;
        }
        // 3、获取请求头token
        String token = request.getHeader("Token");
        log.info("------token: {}", token);

        // 4、判断是否有token 没有响应401
        if(token == null || token.isEmpty()){
            log.info("------token为空，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5、解析token 不合法响应401
        try {
            JwtUtil.parseToken(token);
        } catch (Exception e){
            log.info("------token非法，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 6、token合法 放行
        log.info("------token合法，放行");
        filterChain.doFilter(request, response);
    }
}
