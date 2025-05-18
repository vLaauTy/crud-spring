package com.registro2.CRUD.mac;

public class DispositivoDTO {
    private String ip;
    private String mac;

    public DispositivoDTO(String ip, String mac) {
        this.ip = ip;
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public String getMac() {
        return mac;
    }
}
