package com.registro2.CRUD.mac;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashSet;
import java.util.Set;

@Component
public class ConexionInterceptor implements HandlerInterceptor {

    private static final Set<String> ipsConectadas = new HashSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String ip = request.getRemoteAddr();
        ipsConectadas.add(ip);
        return true;
    }

    public static Set<String> getIpsConectadas() {
        return ipsConectadas;
    }
}
