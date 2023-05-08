/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosinterfaces;

import entidades_consumidor.Supermercadosfavoritos;
import java.util.List;


public interface ISupermercadoFavoritoServicio {
    
    public List<Supermercadosfavoritos> listarTodosLosSupermercadosFavoritos();

    public  boolean guardarSupermercadoFavorito(Supermercadosfavoritos supermercadoFavorito);

    public  Supermercadosfavoritos obtenerSupermercadoFavoritoPorId(int id);

    public boolean actualizarSupermercadoFavorito(Supermercadosfavoritos supermercadoFavorito);

    public boolean eliminarSupermercadoFavorito(int id);
    
    
}
