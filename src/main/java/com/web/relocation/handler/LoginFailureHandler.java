package com.web.relocation.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Configuration
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    //private String defaultFailureUrl;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {

        String errormsg = null;

        if(exception instanceof UsernameNotFoundException) {
            errormsg = "아이디 또는 비밀번호가 잘못 되었습니다.";
        }
        else if(exception instanceof BadCredentialsException) {
            errormsg = "아이디 또는 비밀번호가 잘못 되었습니다.";
        }
        /*else if(exception instanceof DisabledException) {
            errormsg = "인증 되지 않은 계정 입니다.";
        } else if(exception instanceof CredentialsExpiredException) {
            errormsg = "비밀번호 기간 만료 되었습니다.";
        }*/
        /*String username = request.getParameter("username");
        String password = request.getParameter("password");*/

        response.sendRedirect("/account/login/result?error=true&exception=" + URLEncoder.encode(errormsg, "UTF-8")); // Redirect
        //request.getRequestDispatcher("/account/login/result&exception="+errormsg).forward(request, response); // Forward
    }

    /*public void setDefaultFailureUrl(String defaultFailureUrl) {
        this.defaultFailureUrl = defaultFailureUrl;
    }

    public String getDefaultFailureUrl() {
        return this.defaultFailureUrl;
    }*/

}
