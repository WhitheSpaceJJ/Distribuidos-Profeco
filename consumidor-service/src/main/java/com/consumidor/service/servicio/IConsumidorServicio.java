package com.consumidor.service.servicio;

import com.consumidorservice.model.Consumidor;
import java.util.List;


public interface IConsumidorServicio {

    public List<Consumidor> listarTodosLosConsumidores();

    public Consumidor guardarConsumidor(Consumidor consumidor);

    public Consumidor obtenerConsumidorPorId(Long id);

    public Consumidor actualizarConsumidor(Consumidor consumidor);

    public void eliminarConsumidor(Long id);

    
}
