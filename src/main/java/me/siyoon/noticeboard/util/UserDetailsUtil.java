package me.siyoon.noticeboard.util;

import me.siyoon.noticeboard.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public final class UserDetailsUtil {
    public static CustomUserDetails get(){
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails;
    }
}
