package com.web.relocation.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException {
        /*
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            req.getSession(false).setMaxInactiveInterval(1800);
        }
        else {
            req.getSession(false).setMaxInactiveInterval(1800);
        }*/
        req.getSession(false).setMaxInactiveInterval(1800); // session 시간 제한 30분
        res.sendRedirect("/account/login/result");
    }
}
