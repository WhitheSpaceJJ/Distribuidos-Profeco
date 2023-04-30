package com.consumidor.service.servicio;

import com.consumidorservice.model.Consumidor;
import com.consumidorservice.repository.IConsumidorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorServicio implements IConsumidorServicio {

    @Autowired
    private IConsumidorRepository repositorio;

    @Override
    public List<Consumidor> listarTodosLosConsumidores() {
        return (List<Consumidor>) repositorio.findAll();
    }

    @Override
    public Consumidor guardarConsumidor(Consumidor consumidor) {
        return repositorio.save(consumidor);
    }

    @Override
    public Consumidor obtenerConsumidorPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Consumidor actualizarConsumidor(Consumidor consumidor) {
        return repositorio.save(consumidor);
    }

    @Override
    public void eliminarConsumidor(Long id) {
        repositorio.deleteById(id);
    }

}
