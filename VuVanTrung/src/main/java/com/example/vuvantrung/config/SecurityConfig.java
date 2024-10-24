package com.example.vuvantrung.config;

import com.example.vuvantrung.repository.UserRepository;
import com.example.vuvantrung.security.CustomPermissionEvaluator;
import com.example.vuvantrung.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

// --------------------------- this is good version for running app without jwt
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    CustomUserDetailsService customUserDetailsServiceService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        //Sử dụng BCrypt để mã hóa mật khẩu
//        return new BCryptPasswordEncoder();
//    }
//
//    //Cách cũ: WebSecurityConfigurerAdapter
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
////    }
//
//    //Cách mới
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/api/books", "/login", "/testreturnud").permitAll()
//                        .requestMatchers("/TierConfig/**").hasRole("ADMIN")
//                        .requestMatchers("/UsageHistory/**").hasRole("USER")
//                        .anyRequest().authenticated()
//                )
////                .formLogin(form -> form.loginPage("/login")
////                        .permitAll()
////                        .defaultSuccessUrl("/api/books")
////                )
//                .formLogin(formLogin -> formLogin
//                        .permitAll()
//                        .defaultSuccessUrl("/api/books")
//                )
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//}


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserRepository userRepository, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userRepository = userRepository;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Inject UserDetailsService into JwtAuthenticationFilter
        jwtAuthenticationFilter.setUserDetailsService(userDetailsService());

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/auth/**", "/test_async/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/user/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.getWriter().write("Access Denied: " + accessDeniedException.getMessage());
                        })
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Register custom PermissionEvaluator
    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(CustomPermissionEvaluator permissionEvaluator) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(permissionEvaluator);
        return expressionHandler;
    }
}


//.requestMatchers("/api/v1/auth/**", "/test_async/**").permitAll()
//                        .requestMatchers("/api/books").permitAll()
//                        .requestMatchers("/api/tier-config/**").hasRole("ADMIN")
//                        .requestMatchers("/api/usage-history/usage-history-by-user-id/{id}").hasRole("USER")
//                        .requestMatchers("/api/usage-history/usage-history-not-paid-by-user-id/{id}").hasRole("USER")
//                        .requestMatchers("/api/usage-history/pay-lectric-bill-by-id-user-and-month").hasRole("USER")
//                        .requestMatchers("/api/usage-history/create-usage-history").hasRole("ADMIN")