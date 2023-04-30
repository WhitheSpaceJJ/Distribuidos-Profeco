package com.supermercado.service.servicio;

import com.supermercadoservice.model.Consumidor;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicioIntegracion implements IServicioIntegracion {

    @Autowired
    private RestTemplate rest;

    private final String URL_CONSUMIDORES = "http://localhost:8060/consumidor";

    /**
     * Obtiene la lista de consumidores del microservicio externo
     * consumidor-service
     *
     * @return La lista de consumidores.
     */
    @Override
    public List<Consumidor> getConsumidoresFromMicroservice() {
        return Arrays.asList(rest.getForObject(URL_CONSUMIDORES, Consumidor[].class));
    }

    /**
     * Verifica si el consumidor existe en el microservicio consumidor-serivce
     *
     * @param consumidorId El consumidor a buscar si existe.
     * @return true si existe, false en caso contrario.
     */
    @Override
    public boolean consumidorExists(Long consumidorId) {
        return getConsumidoresFromMicroservice()
                .stream()
                .anyMatch(s -> s.getId_consumidores().equals(consumidorId));
    }

}
