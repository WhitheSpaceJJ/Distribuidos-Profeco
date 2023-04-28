package com.supermercadoservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "supermercados")
public class Supermercado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_supermercados;
    private String nombre;
    
    @OneToMany(mappedBy = "supermercado", cascade = CascadeType.ALL)
    
    private List<Comentario> comentarios = new ArrayList<>();
    
    @OneToMany(mappedBy = "supermercado", cascade = CascadeType.ALL)
    private List<Producto> productos = new ArrayList<>();
    
    private String direccion;

    public Supermercado() {
    }

    public Supermercado(Long id, String nombre, String direccion, List<Producto> productos, List<Comentario> comentarios) {
        this.id_supermercados = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.productos = productos;
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id_supermercados;
    }

    public void setId(Long id) {
        this.id_supermercados = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
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
        return "Supermercado{" + "id=" + id_supermercados + ", nombre=" + nombre + ", comentarios=" + comentarios + ", productos=" + productos + ", direccion=" + direccion + '}';
    }

    
    
    
}
