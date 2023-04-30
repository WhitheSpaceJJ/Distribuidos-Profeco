package com.supermercadoservice.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;

public class Consumidor {

    
    private Long id_consumidores;
    private String nombre;
    private String contraseña;
    private String email;
    private int edad;
    
    private List<SupermercadoFavorito> supermercadosfavoritos = new ArrayList<>();

    private List<WishList> wishlist = new ArrayList<>();

    public Consumidor() {
    }

    public Consumidor(Long id_consumidores, String nombre, String contraseña, String email, int edad) {
        this.id_consumidores = id_consumidores;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.email = email;
        this.edad = edad;
    }

    public Consumidor(Long id_consumidores) {
        super();
        this.id_consumidores = id_consumidores;
    }
    

    public Long getId_consumidores() {
        return id_consumidores;
    }

    public void setId_consumidores(Long id_consumidores) {
        this.id_consumidores = id_consumidores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<SupermercadoFavorito> getSupermercadosFavoritos() {
        return supermercadosfavoritos;
    }

    public void setSupermercadosFavoritos(List<SupermercadoFavorito> supermercadosFavoritos) {
        this.supermercadosfavoritos = supermercadosFavoritos;
    }

    public List<WishList> getWishList() {
        return wishlist;
    }

    public void setWishList(List<WishList> wishList) {
        this.wishlist = wishList;
    }

    @Override
    public String toString() {
        return "Consumidor{" + "id_consumidores=" + id_consumidores + ", nombre=" + nombre + ", contrase\u00f1a=" + contraseña + ", email=" + email + ", edad=" + edad + ", supermercadosFavoritos=" + supermercadosfavoritos + ", wishList=" + wishlist + '}';
    }

}
