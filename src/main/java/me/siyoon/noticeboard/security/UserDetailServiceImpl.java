package me.siyoon.noticeboard.security;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Role;
import me.siyoon.noticeboard.domain.User;
import me.siyoon.noticeboard.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUser(email);
        if (user == null) {
            throw new UsernameNotFoundException(email + " is not found");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        CustomUserDetails userDetails = new CustomUserDetails(email, user.getPassword(), authorities);
        userDetails.setNickName(user.getNickName());
        userDetails.setId(user.getId());
        return userDetails;
    }
}
