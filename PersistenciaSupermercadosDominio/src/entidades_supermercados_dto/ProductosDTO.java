/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_supermercados_dto;

import entidades_supermercados.Supermercados;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class ProductosDTO {

    private Integer idProductos;
    private String nombre;
    private String marca;
    private double precio;
    private int stock;
    private Supermercados supermercadoId;

    public ProductosDTO(Integer idProductos, String nombre, String marca, double precio, int stock, Supermercados supermercadoId) {
        this.idProductos = idProductos;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        supermercadoId.setComentariosList(null);
        supermercadoId.setProductosList(null);
        this.supermercadoId = supermercadoId;
    }

    public Integer getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Integer idProductos) {
        this.idProductos = idProductos;
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

    public Supermercados getSupermercadoId() {
        return supermercadoId;
    }

    public void setSupermercadoId(Supermercados supermercadoId) {
        this.supermercadoId = supermercadoId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idProductos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductosDTO other = (ProductosDTO) obj;
        return Objects.equals(this.idProductos, other.idProductos);
    }

    @Override
    public String toString() {
        return "ProductosDTO{" + "idProductos=" + idProductos + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", stock=" + stock + ", supermercadoId=" + supermercadoId + '}';
    }

}
