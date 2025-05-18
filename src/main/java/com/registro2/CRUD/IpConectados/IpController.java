package com.registro2.CRUD.IpConectados;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpController {

    @Autowired
    private IpService ipService;

    @GetMapping("/ips")
    public Set<String> verIpsConectadas() {
        return ipService.obtenerIps();
    }
}
