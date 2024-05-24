package awa.training.api.core.utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
	    public static String getCurrentUsername() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && authentication.isAuthenticated()) {
	            return authentication.getName();
	        } else {
	            throw new RuntimeException("No authenticated user found");
	        }
	    }

}

