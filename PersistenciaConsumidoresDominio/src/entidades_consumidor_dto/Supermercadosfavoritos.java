/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_consumidor_dto;

import entidades_consumidor.Consumidores;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Supermercadosfavoritos {
    private Integer idFavoritos;
    private int supermercadoId;
    private Consumidores consumidorId;

    public Supermercadosfavoritos(Integer idFavoritos, int supermercadoId, Consumidores consumidorId) {
        this.idFavoritos = idFavoritos;
        this.supermercadoId = supermercadoId;
             consumidorId.setSupermercadosfavoritosList(null);
        consumidorId.setWishlistList(null);
        this.consumidorId = consumidorId;
    }

    public Integer getIdFavoritos() {
        return idFavoritos;
    }

    public void setIdFavoritos(Integer idFavoritos) {
        this.idFavoritos = idFavoritos;
    }

    public int getSupermercadoId() {
        return supermercadoId;
    }

    public void setSupermercadoId(int supermercadoId) {
        this.supermercadoId = supermercadoId;
    }

    public Consumidores getConsumidorId() {
        return consumidorId;
    }

    public void setConsumidorId(Consumidores consumidorId) {
        this.consumidorId = consumidorId;
    }

    @Override
    public String toString() {
        return "Supermercadosfavoritos{" + "idFavoritos=" + idFavoritos + ", supermercadoId=" + supermercadoId + ", consumidorId=" + consumidorId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.idFavoritos);
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
        final Supermercadosfavoritos other = (Supermercadosfavoritos) obj;
        return Objects.equals(this.idFavoritos, other.idFavoritos);
    }
    
}
