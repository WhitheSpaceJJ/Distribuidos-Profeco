/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_supermercados_dto;

import entidades_supermercados.Comentarios;
import entidades_supermercados.Productos;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class SupermercadosDTO {

    private Integer idSupermercados;
    private String nombre;
    private String direccion;
    private List<Comentarios> comentariosList;
    private List<Productos> productosList;

    public SupermercadosDTO(Integer idSupermercados, String nombre, String direccion, List<Comentarios> comentariosList, List<Productos> productosList) {
        this.idSupermercados = idSupermercados;
        this.nombre = nombre;
        this.direccion = direccion;
        for (int i = 0; i < productosList.size(); i++) {
            productosList.get(i).setSupermercadoId(null);
        }

        this.comentariosList = comentariosList;
        for (int i = 0; i < comentariosList.size(); i++) {
            comentariosList.get(i).setSupermercadoId(null);
        }
        this.productosList = productosList;
    }

    public Integer getIdSupermercados() {
        return idSupermercados;
    }

    public void setIdSupermercados(Integer idSupermercados) {
        this.idSupermercados = idSupermercados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Comentarios> getComentariosList() {
        return comentariosList;
    }

    public void setComentariosList(List<Comentarios> comentariosList) {
        this.comentariosList = comentariosList;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idSupermercados);
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
        final SupermercadosDTO other = (SupermercadosDTO) obj;
        return Objects.equals(this.idSupermercados, other.idSupermercados);
    }

    @Override
    public String toString() {
        return "SupermercadosDTO{" + "idSupermercados=" + idSupermercados + ", nombre=" + nombre + ", direccion=" + direccion + ", comentariosList=" + comentariosList + ", productosList=" + productosList + '}';
    }

}
