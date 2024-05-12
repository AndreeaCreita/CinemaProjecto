package com.example.CinemaProjecto.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
////import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
////import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Allows unrestricted access to all URLs.


                )
                .csrf(csrf -> csrf.disable()) // Disables CSRF protection for APIs or where you handle it differently.
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .formLogin(form -> form
                        .loginPage("/login") // Specifies the custom login page, if you have one.
                        .permitAll() // Allows access to the login page without being authenticated.
                        .defaultSuccessUrl("/", true) // Redirects to a specific URL after a successful login.

                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/login"))
                .logout(logout -> logout
                        .logoutUrl("/logout") // Specifies the logout URL.
                        .logoutSuccessUrl("/login?logout") // Where to go after logout is successful.
                        .permitAll() // Allows access to logout for everyone.
                )
                .httpBasic(httpBasic -> httpBasic.disable()); // Disables basic authentication.

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 //   @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/**").permitAll();
//                } )
//                .csrf(AbstractHttpConfigurer::disable)
//                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .sessionManagement(httpSecuritySessionManagementConfigurer -> {
//            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        });
//        return http.build();
//    }

}
















/*@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable(); // Disable X-Frame-Options for H2 Console (if used)
    }
}
*/

