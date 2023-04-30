/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consumidor.service.servicio;

import com.consumidorservice.model.Supermercado;
import com.consumidorservice.model.SupermercadoFavorito;
import java.util.List;


public interface ISupermercadoFavoritoServicio {
    
    public List<SupermercadoFavorito> listarTodosLosSupermercadosFavoritos();

    public SupermercadoFavorito guardarSupermercadoFavorito(SupermercadoFavorito supermercadoFavorito);

    public SupermercadoFavorito obtenerSupermercadoFavoritoPorId(Long id);

    public SupermercadoFavorito actualizarSupermercadoFavorito(SupermercadoFavorito supermercadoFavorito);

    public void eliminarSupermercadoFavorito(Long id);
    
}
