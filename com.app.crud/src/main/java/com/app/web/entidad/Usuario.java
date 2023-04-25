package com.app.web.entidad;

import javax.persistence.*;

@Entity
@Table(name= "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String contrasenha;

    private String tipo;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String contrasenha, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenha = contrasenha;
        this.tipo = tipo;
    }

    public Usuario(String nombre, String contrasenha, String tipo) {
        this.nombre = nombre;
        this.contrasenha = contrasenha;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contrasenha='" + contrasenha + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
