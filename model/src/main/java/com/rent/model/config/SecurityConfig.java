package com.rent.model.config;

import com.rent.model.repository.RoleRepository;
import com.rent.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/registration")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/images/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//    ------------------------------------------------------------------------


//public class SecurityConfig extends WebSecurityConfigurerAdapter {
////    @Autowired
////    private UserDetailsServiceImpl userDetailsService;
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private RoleRepository roleRepository;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//
////        Role role = new Role();
////        role.setRole("ROLE_ADMIN");
////
////        Users user = new Users();
////        user.setUserName("admin");
////        user.setPassword("admin");
////        user.setRole(role);
////
////        roleRepository.save(role);
////        userRepository.save(user);
////
////        auth.userDetailsService(userDetailsService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.httpBasic().realmName("Rent").authenticationEntryPoint((request, response, authException) -> {
//            String requestBy = request.getHeader("X-Requested-By");
//            System.out.println(requestBy);
//            if(requestBy == null || requestBy.isEmpty()) {
//                HttpServletResponse httpServletResponse = response;
//                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, authException.getMessage());
//            } else {
//                HttpServletResponse httpServletResponse = response;
//                httpServletResponse.addHeader("WWW-Authenticate", "Application driven");
//                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            }
//        })
//                .and().authorizeRequests()
//                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                    .antMatchers("/user/edit/*").access("hasRole('ADMIN') or hasRole('USER')")
//                    .antMatchers("/admin/*").hasRole("ADMIN")
//                    .anyRequest().permitAll()
//                .and().formLogin().permitAll() // scris de mine
//                .and().logout()
//                .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
//    }
//
//    // .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringAntMatchers("")
//
//    public class CsrfHeaderFilter extends OncePerRequestFilter {
//
//        @Override
//        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//            CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//            if (csrf != null) {
//                Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//                String token = csrf.getToken();
//                String id = request.getSession(true) != null ? request.getSession(false).getId() : "<NO SESSION>";
//                if (cookie == null || token != null && !token.equals(cookie.getValue())) {
//                    cookie = new Cookie("XSRF-TOKEN", token);
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                }
//                response.setHeader("X-XSRF-TOKEN", token);
//                response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
//                response.setHeader("Access-Control-Max-Age", "3600");
//                response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, x-xsrf-token, x-auth-token, X-Auth-Token, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, authorization");
//            }
//            filterChain.doFilter(request, response);
//        }
//    }
//}
