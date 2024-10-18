package com.example.vuvantrung.config;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}admin")
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password("{noop}user")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

import com.example.vuvantrung.repository.UserRepository;
import com.example.vuvantrung.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                        .requestMatchers("/api/books").permitAll()
                        .requestMatchers("/TierConfig/**").hasRole("ADMIN")
                        .requestMatchers("/UsageHistory/usage_history_by_user_id/{id}").hasRole("USER")
                        .requestMatchers("/UsageHistory/usage_history_not_paid_by_user_id/{id}").hasRole("USER")
                        .requestMatchers("/UsageHistory/pay_electric_bill_by_id_user_and_month").hasRole("USER")
                        .requestMatchers("/UsageHistory/create_usage_history").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
}


