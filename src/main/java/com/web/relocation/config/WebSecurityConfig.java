package com.web.relocation.config;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.web.relocation.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value="loginFailureHandler")
    private AuthenticationFailureHandler loginFailureHandler;

    @Autowired
    @Qualifier(value="loginSuccessHandler")
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private UserDetailsService accountService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // resources 디렉터리 하위 파일 목록은 인증 무시(=항상통과)
        web.ignoring().antMatchers("resources/static/css/**", "resources/static/js/**", "resources/static/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/account/login")
                    .loginProcessingUrl("/account/login")
                    //.defaultSuccessUrl("/account/login/result")
                    //.successHandler(new LoginSuccessHandler())
                    .successHandler(loginSuccessHandler)
                    .failureUrl("/account/login/result?error=true")
                    .failureHandler(loginFailureHandler)
                    .usernameParameter("userId")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/account/logout")
                    .logoutSuccessUrl("/account/login")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                    .csrf()
                    .disable()
                    .exceptionHandling().accessDeniedPage("/account/denied")
                .and()
                    .headers()
                    .frameOptions()
                    .sameOrigin()
                .and()
                    .sessionManagement()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true)
                    .sessionRegistry(sessionRegistry());
                    //.expiredUrl("/account/login/result");
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // encoding 기본형
        String idForEncode = "sha2";
        final String key = "relocationAdmin";
        BaseEncoding base64 = BaseEncoding.base64();


        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("sha2", new PasswordEncoder(){

            @Override
            public String encode(CharSequence rawPassword) {
                return Hashing.sha256().hashString(rawPassword.toString(), Charsets.UTF_8).toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        });

        encoders.put("sha3", new PasswordEncoder(){
            @Override
            public String encode(CharSequence rawPassword) {
                return Hashing.sha512().hashString(rawPassword.toString(), Charsets.UTF_8).toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        });

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }
}
