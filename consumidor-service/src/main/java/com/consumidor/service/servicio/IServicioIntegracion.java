package com.consumidor.service.servicio;

import com.consumidorservice.model.Supermercado;
import java.util.List;

public interface IServicioIntegracion {

    public List<Supermercado> getSupermercadosFromMicroservice();

    public boolean supermercadoExists(Long supermercadoId);
    
}
