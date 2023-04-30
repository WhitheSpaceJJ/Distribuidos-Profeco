/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consumidorservice.repository;

import com.consumidorservice.model.SupermercadoFavorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISupermercadoFavoritoRepository extends JpaRepository<SupermercadoFavorito, Long>{
    
}
