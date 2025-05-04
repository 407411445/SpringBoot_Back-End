package com.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigure {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//
//        }
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        return new AuthenticationManager() {
//
//        }
//    }

    /**
     * spring security 6.1之後的新寫法 重要!!!!!
     * @return http
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                 .csrf(
                         AbstractHttpConfigurer::disable
                 )
                 .headers(
                         httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
                                 HeadersConfigurer.FrameOptionsConfig::sameOrigin
                         )
                 )
                 .authorizeHttpRequests(
                     auth -> auth.requestMatchers("/h2-console/**").permitAll()
                             .requestMatchers("/api/**").permitAll()
                            .anyRequest().authenticated()
                 )
//                 .formLogin(httpSecurityFormLoginConfigurer ->
//                     httpSecurityFormLoginConfigurer.permitAll()
//                                 .defaultSuccessUrl("/h2-ui",true)
//                 )
                 .httpBasic(
                    Customizer.withDefaults()
                 )
                .logout(
                        httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                                .logoutUrl("/logout").permitAll()
                );
        return http.build();
    }

}
