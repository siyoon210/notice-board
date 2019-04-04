package me.siyoon.noticeboard.security;

import lombok.Getter;
import lombok.Setter;
import me.siyoon.noticeboard.domain.enums.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUserDetails extends User {
    private Long id;
    private String nickName;

    public CustomUserDetails(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
    }

    public String getEmail() {
        return super.getUsername();
    }

    public boolean hasAuthority(Authority authority) {
        Collection<GrantedAuthority> authorities = super.getAuthorities();

        return authorities.contains(new SimpleGrantedAuthority("ROLE_" + authority.name()));
    }
}
