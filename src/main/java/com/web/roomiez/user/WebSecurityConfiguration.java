package com.web.roomiez.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
    //TODO: add custom authentication requirements for certain users
    //TODO: adapt to our form login -> need login controller
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home/**").permitAll()
                .requestMatchers("/user/confirm/**").permitAll()
                .requestMatchers("/user/registration").permitAll()
                .requestMatchers("/user/registration/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin();

//                //.csrf((csrf) -> csrf.ignoringRequestMatchers("/**") )
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers(new AntPathRequestMatcher("/user/registration/**")).permitAll()
//                                .requestMatchers("/user/registration/**").permitAll()
//                                .anyRequest().authenticated().and()
//                        .requestMatchers("/Admin").permitAll()
//                        .anyRequest().authenticated()
//                        .requestMatchers("/Admin").permitAll()
//                        .anyRequest().authenticated().and()
//                        .formLogin().loginPage("/login").permitAll().and().logout().permitAll()

//                )
                //.httpBasic();


        return http.build();
    }

}
