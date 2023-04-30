package com.consumidorservice.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;


@Embeddable
public class Supermercado {
    
    
    private Long supermercado_id;
    
    @Embedded
    private String nombre;

    @Embedded
    private List<Producto> productos = new ArrayList<>();
    
    @Embedded
    private String direccion;

    public Supermercado() {
    }

    public Supermercado(Long id, String nombre, String direccion, List<Producto> productos) {
        this.supermercado_id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.productos = productos;
    }

    public Long getId() {
        return supermercado_id;
    }

    public void setId(Long id) {
        this.supermercado_id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Supermercado{" + "id=" + supermercado_id + ", nombre=" + nombre + ", productos=" + productos + ", direccion=" + direccion + '}';
    }

    
    
    
}
