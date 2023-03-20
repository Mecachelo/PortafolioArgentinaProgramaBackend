package com.portafolio.mem.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Marcelo
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 0, max = 44, message = "El campo no cumple los requisitos")
    private String nombre;

    @NotNull
    @Size(min = 0, max = 44, message = "El campo no cumple los requisitos")
    private String apellido;

    @NotNull
    private String descripcion;

    @NotNull
    private String imagen;

    //Constructor
    public User() {
    }

    public User(String nombre, String apellido, String descripcion, String imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    //Getter&Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
