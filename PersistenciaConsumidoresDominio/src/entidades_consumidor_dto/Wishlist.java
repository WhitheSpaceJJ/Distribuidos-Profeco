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
public class Wishlist {
   
    private Integer idWishlist;
    private String deseo;
    private int supermercadoId;
    private Consumidores consumidorId;

    public Wishlist(Integer idWishlist, String deseo, int supermercadoId, Consumidores consumidorId) {
        this.idWishlist = idWishlist;
        this.deseo = deseo;
        this.supermercadoId = supermercadoId;
        consumidorId.setSupermercadosfavoritosList(null);
        consumidorId.setWishlistList(null);
        this.consumidorId = consumidorId;
        
        
    }

    public Integer getIdWishlist() {
        return idWishlist;
    }

    public void setIdWishlist(Integer idWishlist) {
        this.idWishlist = idWishlist;
    }

    public String getDeseo() {
        return deseo;
    }

    public void setDeseo(String deseo) {
        this.deseo = deseo;
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
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idWishlist);
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
        final Wishlist other = (Wishlist) obj;
        return Objects.equals(this.idWishlist, other.idWishlist);
    }

    @Override
    public String toString() {
        return "Wishlist{" + "idWishlist=" + idWishlist + ", deseo=" + deseo + ", supermercadoId=" + supermercadoId + ", consumidorId=" + consumidorId + '}';
    }

    
    
}
