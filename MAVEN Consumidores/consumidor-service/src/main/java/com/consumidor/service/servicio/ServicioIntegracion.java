/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consumidor.service.servicio;


import entidades.entidades_supermercados.Supermercados;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicioIntegracion implements IServicioIntegracion {

    @Autowired
    private RestTemplate rest;

    private final String URL_SUPERMERCADOS = "http://localhost:8060/supermercado";

    /**
     * Obtiene la lista de supermercados del microservicio externo
     * supermercado-service
     *
     * @return La lista de supermercados.
     */
    @Override
    public List<Supermercados> getSupermercadosFromMicroservice() {
        return Arrays.asList(rest.getForObject(URL_SUPERMERCADOS, Supermercados[].class));
    }

    /**
     * Verifica si el supermercado existe en el microservicio
     * supermercado-serivce
     *
     * @param supermercadoId El supermercado a buscar si existe.
     * @return true si existe, false en caso contrario.
     */
    @Override
    public boolean supermercadoExists(int supermercadoId) {
        return getSupermercadosFromMicroservice()
                .stream()
                .anyMatch(s -> s.getIdSupermercados().equals(supermercadoId));
    }

}
