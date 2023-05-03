/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosinterfaces;

import entidades_supermercados.Supermercados;
import java.util.List;


public interface ISupermercados {
    
    public List<Supermercados > listarTodosLosSupermercados();

    public  boolean guardarSupermercados(Supermercados supermercadoFavorito);

    public  Supermercados obtenerSupermercadosPorId(int id);

    public boolean actualizarSupermercados(Supermercados supermercadoFavorito);

    public boolean eliminarSupermercados(int id);
    
    
}
