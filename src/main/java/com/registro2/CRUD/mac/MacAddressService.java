package com.registro2.CRUD.mac;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MacAddressService {

    public List<DispositivoDTO> obtenerDispositivosConectados() {
        List<DispositivoDTO> dispositivos = new ArrayList<>();
        Set<String> ipsFiltradas = ConexionInterceptor.getIpsConectadas();

        try {
            Process arp = Runtime.getRuntime().exec("arp -a");
            BufferedReader reader = new BufferedReader(new InputStreamReader(arp.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().matches("^(\\d+\\.){3}\\d+\\s+([\\da-fA-F]{2}[-:]){5}[\\da-fA-F]{2}.*")) {
                    String[] partes = line.trim().split("\\s+");
                    String ip = partes[0];
                    String mac = partes[1];
                    if (ipsFiltradas.contains(ip)) {
                        dispositivos.add(new DispositivoDTO(ip, mac));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dispositivos;
    }
}
