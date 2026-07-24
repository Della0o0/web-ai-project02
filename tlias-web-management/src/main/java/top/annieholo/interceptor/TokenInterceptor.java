package top.annieholo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.annieholo.utils.jwt.JwtUtil;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // return HandlerInterceptor.super.preHandle(request, response, handler);
        // HttpServletRequest request = (HttpServletRequest) servletRequest;
        // HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1、获取请求路径
        // String remoteAddr = servletRequest.getRemoteAddr();
        // log.info("remoteAddr: {}", remoteAddr);
        String requestURI = request.getRequestURI();
        log.info("-------preHandle requestURI: {}", requestURI);
        // 2、判断是否是登陆请求 /login 放行
        // if(requestURI.contains("/login")){
        //     // filterChain.doFilter(request, response);
        //     return true;
        // }
        // 3、获取请求头token
        String token = request.getHeader("Token");
        log.info("------preHandle token: {}", token);

        // 4、判断是否有token 没有响应401
        if(token == null || token.isEmpty()){
            log.info("------preHandle token为空，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 5、解析token 不合法响应401
        try {
            JwtUtil.parseToken(token);
        } catch (Exception e){
            log.info("------preHandle token非法，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 6、token合法 放行
        log.info("------preHandle token合法，放行");
        // filterChain.doFilter(request, response);
        return true;
    }
}
