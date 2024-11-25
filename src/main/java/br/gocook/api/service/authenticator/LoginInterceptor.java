package br.gocook.api.service.authenticator;

import br.gocook.api.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if(CookieService.getCookie(request, "usuarioId") != null){
            return true;
        }

        response.sendRedirect("http://127.0.0.1:5500/app/telaLogin/login.html");
        return false;
    }
}
