package com.rent.model.security;

import com.rent.model.entity.Role;
import com.rent.model.entity.Users;
import com.rent.model.repository.RoleRepository;
import com.rent.model.repository.UserRepository;
import com.rent.model.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        Role role = new Role();
        role.setRole("ROLE_ADMIN");

        Users user = new Users();
        user.setUserName("admin");
        user.setPassword("admin");
        user.setRole(role);

        roleRepository.save(role);
        userRepository.save(user);

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().realmName("Rent").authenticationEntryPoint((request, response, authException) -> {
            String requestBy = request.getHeader("X-Requested-By");
            if(requestBy == null || requestBy.isEmpty()) {
                HttpServletResponse httpServletResponse = response;
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, authException.getMessage());
            } else {
                HttpServletResponse httpServletResponse = response;
                httpServletResponse.addHeader("WWW-Authenticate", "Application driven");
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        })
                .and().authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/user/add", "/login", "/user/*", "/car/*").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/user/delete/*").hasRole("ADMIN")
                .and().logout()
                .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    }

    // .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringAntMatchers("")

    public class CsrfHeaderFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            if (csrf != null) {
                Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                String token = csrf.getToken();
                String id = request.getSession(true) != null ? request.getSession(false).getId() : "<NO SESSION>";
                if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                    cookie = new Cookie("XSRF-TOKEN", token);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                response.setHeader("X-XSRF-TOKEN", token);
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, x-xsrf-token, x-auth-token, X-Auth-Token, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, authorization");
            }
            filterChain.doFilter(request, response);
        }
    }
}
