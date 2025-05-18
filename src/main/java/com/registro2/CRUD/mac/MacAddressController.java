package com.registro2.CRUD.mac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mac")
public class MacAddressController {

    @Autowired
    private MacAddressService macAddressService;

    @GetMapping("/list")
    public List<DispositivoDTO> obtenerListaDispositivosConectados() {
        return macAddressService.obtenerDispositivosConectados();
    }
}
