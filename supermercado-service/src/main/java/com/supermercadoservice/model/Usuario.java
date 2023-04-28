package com.supermercadoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "usuarios")
public record Usuario(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id_usuarios, 
        String email, String contraseña, String nombre, int edad) {

}
