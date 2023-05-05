package com.profeco.service.servicio;

import entidades.oficial.*;
import java.util.List;

public interface IServicioIntegracion {

    public List<Supermercados> getSupermercadosFromMicroservice();

    public boolean supermercadoExists(int supermercadoId);
}
