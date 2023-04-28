package com.supermercado.service.servicio;

import com.supermercadoservice.model.Supermercado;
import java.util.List;


public interface ISupermercadoServicio {

    public List<Supermercado> listarTodosLosSupermercados();

    public Supermercado guardarSupermercado(Supermercado supermercado);

    public Supermercado obtenerSupermercadoPorId(Long id);

    public Supermercado actualizarSupermercado(Supermercado supermercado);

    public void eliminarSupermercado(Long id);

    
}
