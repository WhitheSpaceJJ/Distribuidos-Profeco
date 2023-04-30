package com.supermercadoservice.model;



public class SupermercadoFavorito {

    private Long id_favoritos;

    private Consumidor consumidor_id;
    

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
