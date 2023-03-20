/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.security.dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Marcelo
 */
public class LoginUsuario {

    @NotBlank
    private String usuario;
    @NotBlank
    private String password;

    //Getter & Setter
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
