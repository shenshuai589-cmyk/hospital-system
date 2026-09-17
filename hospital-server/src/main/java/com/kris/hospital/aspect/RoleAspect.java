package com.kris.hospital.aspect;
import com.kris.hospital.annotation.RequireRole;
import com.kris.hospital.exception.BusinessException;
import com.kris.hospital.utils.UserContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoleAspect {

    @Before("@annotation(requireRole)")
    public void checkRole(JoinPoint joinPoint, RequireRole requireRole){
        String currentRole = UserContext.getRole();

        String requiredRole = requireRole.value();

        if (!requiredRole.equals(currentRole)) {
            throw new BusinessException(403, "无权限访问");
        }
    }
}
