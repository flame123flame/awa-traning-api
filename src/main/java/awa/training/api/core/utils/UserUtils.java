package awa.training.api.core.utils;

import awa.training.api.core.configs.ApplicationConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserUtils {
    private static ApplicationConfig applicationConfig;

    public static String getCurrentUsername() {
        String currentUserId;
        if (Objects.nonNull(applicationConfig) && Objects.nonNull(applicationConfig.getSecurity()) && !applicationConfig.getSecurity().isEnable()) {
            currentUserId = "SYSTEM";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                currentUserId = authentication.getName();
            } else {
                throw new RuntimeException("No authenticated user found");
            }
        }
        return currentUserId;
    }

}

