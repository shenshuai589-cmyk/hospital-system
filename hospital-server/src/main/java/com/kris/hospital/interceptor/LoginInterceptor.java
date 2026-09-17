package com.kris.hospital.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kris.hospital.utils.JwtUtils;
import com.kris.hospital.utils.UserContext;
import com.kris.hospital.vo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private  JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            writeError(response, "请先登录");
            return false;
        }
        String token = authorization.substring(7);

        try{
            Claims claims = jwtUtils.parseToken(token);
            Long userId = Long.valueOf(claims.getSubject());
            String role = claims.get("role", String.class);
            UserContext.setUserId(userId);
            UserContext.setRole(role);
            return true;

        }catch (Exception e){
            writeError(response, "登录凭证已失效，请重新登录");
            return false;
        }
    }

    /**
     * 返回统一错误结果
     */
    private void writeError(
            HttpServletResponse response,
            String message) {

        try {

            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");

            Result<Void> result = Result.error(401, message);

            response.getWriter().write(
                    objectMapper.writeValueAsString(result)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
