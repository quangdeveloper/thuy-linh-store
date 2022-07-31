package vn.free.register.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.free.register.security.UserPrincipal;

public class SecurityUtil {

    public SecurityUtil() {
    }

    public static Long getCurrentUserId() {

        final Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication != null){

            final Object o = authentication.getPrincipal();

            if (o instanceof UserPrincipal){
                UserPrincipal userPrincipal = (UserPrincipal) o;
                return userPrincipal.getId();
            }
        }
        return 0L;
    }

    public static String getCurrentUsernameId() {

        final Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication != null){

            final Object o = authentication.getPrincipal();

            if (o instanceof UserPrincipal){
                UserPrincipal userPrincipal = (UserPrincipal) o;
                return userPrincipal.getUsername();
            }
        }
        return "admin";
    }
}
