package me.siyoon.noticeboard.util;

import me.siyoon.noticeboard.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserDetailsUtil {
    public static CustomUserDetails get(){
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // TODO throws principal null
//        if (userDetails == null) {
//            throw new DisabledException("Fail to load principal from SecurityContextHolder");
//        }

        return userDetails;
    }
}
