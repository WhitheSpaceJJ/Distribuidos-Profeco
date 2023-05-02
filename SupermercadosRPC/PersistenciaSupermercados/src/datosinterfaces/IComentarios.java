package datosinterfaces;

import entidades_consumidor.Consumidores;
import entidades_supermercados.Comentarios;
import java.util.List;


public interface IComentarios {

    public List<Comentarios> listarTodosLosComentarios();

    public  boolean guardarComentarios(Comentarios consumidor);

    public Comentarios obtenerComentariosPorId(int id);

    public boolean actualizarComentarios(Comentarios consumidor);

    public boolean eliminarComentarios(int id);

    
}
