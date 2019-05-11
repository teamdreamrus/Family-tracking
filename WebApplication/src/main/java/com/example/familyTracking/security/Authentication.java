package com.example.familyTracking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
class Authentication extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/").hasRole(Role.USER.toString())
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/").failureUrl("/login?error=true").and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll().and()
                .httpBasic();
    }

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder userPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth){
        try {
            auth.userDetailsService(userService).passwordEncoder(userPasswordEncoder());
        }
        catch(java.lang.Exception ex){
            System.out.println(ex.toString());
        }
    }
}
