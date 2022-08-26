package com.example.touroperators.security.UserDetails;

import com.example.touroperators.models.User;
import com.example.touroperators.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //log.warn(userName);
        User user = userService.getUserByUserName(userName);

        //log.warn(user.getId().toString());
        return new UserDetailsImpl(user);
    }
}
