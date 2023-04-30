
package com.supermercado.service.servicio;

import com.supermercadoservice.model.Consumidor;
import java.util.List;


public interface IServicioIntegracion {
    public List<Consumidor> getConsumidoresFromMicroservice();

    public boolean consumidorExists(Long consumidorId);
}
