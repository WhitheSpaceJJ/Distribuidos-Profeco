package com.consumidorservice.model;

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

@Entity
@Table(name = "supermercadosfavoritos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SupermercadoFavorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_favoritos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consumidor_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Consumidor consumidor_id;
    
    @Column(name = "supermercado_id")
    private Long supermercadosfavoritos;


    public SupermercadoFavorito() {
        super();
    }

    public SupermercadoFavorito(Long id_favoritos, Long supermercado_id, Consumidor consumidor_id) {
        this.id_favoritos = id_favoritos;
        this.supermercadosfavoritos = supermercado_id;
        this.consumidor_id = consumidor_id;
    }

    public SupermercadoFavorito(Long id_favoritos, Consumidor consumidor_id, Long supermercadosfavoritos) {
        this.id_favoritos = id_favoritos;
        this.consumidor_id = consumidor_id;
        this.supermercadosfavoritos = supermercadosfavoritos;
    }
    
    

    public Long getId_favoritos() {
        return id_favoritos;
    }

    public void setId_favoritos(Long id_favoritos) {
        this.id_favoritos = id_favoritos;
    }

    public Long getSupermercado_id() {
        return supermercadosfavoritos;
    }

    public void setSupermercado_id(Long supermercado_id) {
        this.supermercadosfavoritos = supermercado_id;
    }

    public Consumidor getConsumidor_id() {
        return consumidor_id;
    }

    public void setConsumidor_id(Consumidor consumidor_id) {
        this.consumidor_id = consumidor_id;
    }

    @Override
    public String toString() {
        return "SupermercadoFavorito{" + "id_favoritos=" + id_favoritos + ", supermercado_id=" + supermercadosfavoritos + ", consumidor_id=" + consumidor_id + '}';
    }

}
