package com.example.touroperators.security.UserDetails;

import com.example.touroperators.models.User;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    @Getter
    private final Long id;

    private final String userName;

    private final String password;

    private final Collection<? extends GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(User user){

        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();

        grantedAuthorities = List.of(
                new SimpleGrantedAuthority(user
                        .getUserRole()
                        .name())
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
