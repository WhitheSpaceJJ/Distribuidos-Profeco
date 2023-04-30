package com.supermercadoservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


public class WishList {


    private Long id_wishlist;
    private String deseo;

    

    private Consumidor consumidor_id;

    private Long supermercado_id;

    public WishList() {
    }

    public WishList(Long id_wishlist, String deseo, Consumidor consumidor_id, Long supermercado_id) {
        this.id_wishlist = id_wishlist;
        this.deseo = deseo;
        this.consumidor_id = consumidor_id;
        this.supermercado_id = supermercado_id;
    }

    public Long getId_wishlist() {
        return id_wishlist;
    }

    public void setId_wishlist(Long id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public String getDeseo() {
        return deseo;
    }

    public void setDeseo(String deseo) {
        this.deseo = deseo;
    }

    public Consumidor getConsumidor_id() {
        return consumidor_id;
    }

    public void setConsumidor_id(Consumidor consumidor_id) {
        this.consumidor_id = consumidor_id;
    }

    public Long getSupermercado_id() {
        return supermercado_id;
    }

    public void setSupermercado_id(Long supermercado_id) {
        this.supermercado_id = supermercado_id;
    }

    @Override
    public String toString() {
        return "WishList{" + "id_wishlist=" + id_wishlist + ", deseo=" + deseo + ", consumidor_id=" + consumidor_id + ", supermercado_id=" + supermercado_id + '}';
    }

}
