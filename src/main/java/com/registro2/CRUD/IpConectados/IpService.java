package com.registro2.CRUD.IpConectados;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class IpService {

    private final Set<String> ipsConectadas = new HashSet<>();

    public void registrarIp(String ip) {
        ipsConectadas.add(ip);
    }

    public Set<String> obtenerIps() {
        return ipsConectadas;
    }
}
