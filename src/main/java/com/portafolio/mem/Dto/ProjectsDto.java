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
public class ProjectsDto {
    
    @NotBlank
    private String nombre;
    @NotBlank
    private String fecha;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String imagen;
    
    
    //Constructores

    public ProjectsDto() {
    }

    public ProjectsDto(String nombre, String fecha, String descripcion, String imagen) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    
    
    //Getter&Setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
    
    
}
