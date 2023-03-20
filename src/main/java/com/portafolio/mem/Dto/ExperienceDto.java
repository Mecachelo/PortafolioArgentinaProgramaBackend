/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Marcelo
 */
public class ExperienceDto {
    @NotBlank
    private String nombreExp;
    @NotBlank
    private String descripcionExp;
    
    //Constructor

    public ExperienceDto() {
    }

    public ExperienceDto(String nombreExp, String descripcionExp) {
        this.nombreExp = nombreExp;
        this.descripcionExp = descripcionExp;
    }
    
    //Getter & Setter

    public String getNombreExp() {
        return nombreExp;
    }

    public void setNombreExp(String nombreExp) {
        this.nombreExp = nombreExp;
    }

    public String getDescripcionExp() {
        return descripcionExp;
    }

    public void setDescripcionExp(String descripcionExp) {
        this.descripcionExp = descripcionExp;
    }
    
    
}
