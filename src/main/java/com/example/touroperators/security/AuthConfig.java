package com.example.touroperators.security;

import com.example.touroperators.security.Jwt.JwtTokenFilter;
import com.example.touroperators.security.UserDetails.UserDetailsServiceImpl;
import com.example.touroperators.security.access.Abstract.IPermissionResolver;
import com.example.touroperators.services.UserService;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private  final JwtTokenFilter jwtTokenFilter;

    private final UserDetailsService userDetailsService;

    private final IPermissionResolver permissionResolver;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/user/{userId}")
                    .access("@permissionResolver.hasUserPermission(authentication, #userId )")
                .antMatchers(HttpMethod.DELETE, "/user/{userId}")
                    .access("@permissionResolver.hasUserPermission(authentication, #userId )")
                .antMatchers(HttpMethod.GET, "/tour/{tourId}")
                    .authenticated()
                .antMatchers("/tour/{tourId}")
                   .access("hasAnyRole('Worker', 'Owner') and @permissionResolver.hasTourPermission(authentication, #tourId )")
                .antMatchers(HttpMethod.GET, "/tour-operator/{tourOperatorId}")
                    .authenticated()
                .antMatchers("/tour-operator/{tourOperatorId}")
                    .access("hasRole('Owner') and @permissionResolver.hasTourOperatorPermission(authentication, #tourOperatorId )")
                .antMatchers("/tour/{tourId}/user/{userId}")
                    .access("@permissionResolver.hasUserPermission(authentication, #userId)")
                .antMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated();

         http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
                username -> Optional.of(userDetailsService.loadUserByUsername(username))
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User " + username + " not found.")));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
