/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.consumidor.service.servicio;

import entidades.oficial.Supermercados;
import entidades.oficial.*;
import java.util.List;

public interface IServicioIntegracion {

    public List<Supermercados> getSupermercadosFromMicroservice();

    public boolean supermercadoExists(int supermercadoId);
    
}