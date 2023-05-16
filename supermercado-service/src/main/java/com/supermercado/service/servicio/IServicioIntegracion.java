
package com.supermercado.service.servicio;
import entidades.oficial.Consumidores;
import java.util.List;


public interface IServicioIntegracion {
    public List<Consumidores> getConsumidoresFromMicroservice();

    public boolean consumidorExists(int consumidorId);
}
