package datosinterfaces;

import entidades_consumidor.Consumidores;
import java.util.List;


public interface IConsumidorServicio {

    public List<Consumidores> listarTodosLosConsumidores();

    public  boolean guardarConsumidor(Consumidores consumidor);

    public Consumidores obtenerConsumidorPorId(int id);

    public boolean actualizarConsumidor(Consumidores consumidor);

    public boolean eliminarConsumidor(int id);

    
}
