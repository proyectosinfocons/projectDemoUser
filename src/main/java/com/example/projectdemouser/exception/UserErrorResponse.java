package com.example.projectdemouser.exception;

public class UserErrorResponse {

    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public UserErrorResponse(String mensaje) {
        this.mensaje = mensaje;
    }
}