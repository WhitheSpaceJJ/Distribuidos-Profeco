/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercadoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_productos;
    private String nombre;
    private String marca;
    private double precio;
    private int stock;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supermercado_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Supermercado supermercado;

    public Producto() {

    }

    public Producto(Long id, String nombre, String marca, double precio, int stock, Supermercado supermercado) {
        this.id_productos = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.supermercado = supermercado;
    }

    public Long getId() {
        return id_productos;
    }

    public void setId(Long id) {
        this.id_productos = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id_productos + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", stock=" + stock + ", supermercado=" + supermercado + '}';
    }
    

}
