/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercadoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name= "comentarios")
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comentarios;
    private Long usuarioId;
    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supermercado_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long supermercado_id;
    
    private String mensaje;

    
    public Comentario(){
        
    }
    
    public Comentario(Long id, Long usuarioId, Long supermercado_id, String mensaje) {
        this.id_comentarios = id;
        this.usuarioId = usuarioId;
        this.supermercado_id = supermercado_id;
        this.mensaje = mensaje;
    }

    public Long getId() {
        return id_comentarios;
    }

    public void setId(Long id) {
        this.id_comentarios = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getSupermercadoId() {
        return supermercado_id;
    }

    public void setSupermercadoId(Long supermercado_id) {
        this.supermercado_id = supermercado_id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id=" + id_comentarios + ", usuarioId=" + usuarioId + ", supermercado_id=" + supermercado_id + ", mensaje=" + mensaje + '}';
    }
    
    
    
}
