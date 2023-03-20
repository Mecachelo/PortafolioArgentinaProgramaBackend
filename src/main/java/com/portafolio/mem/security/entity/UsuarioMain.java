/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Marcelo
 */
@Entity
public class UsuarioMain implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String usuario;
    private String email;
    private String password;
    @ElementCollection
    @OneToMany(targetEntity = UsuarioMain.class)
    private Collection<? extends GrantedAuthority> authorities;

    //Constructor
    public UsuarioMain(String nombre, String usuario, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {

        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UsuarioMain build(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRolNombre()
                .name())).collect(Collectors.toList());

        return new UsuarioMain(usuario.getNombre(), usuario.getUsuario(),
                usuario.getEmail(), usuario.getPassword(), authorities);
    }

    public UsuarioMain() {
    }

    //Overrides
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
