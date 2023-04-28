package com.supermercado.service.servicio;

import com.supermercadoservice.model.Supermercado;
import com.supermercadoservice.repository.ISupermercadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupermercadoServicio implements ISupermercadoServicio{

    @Autowired
    private ISupermercadoRepository repositorio;
    
    @Override
    public List<Supermercado> listarTodosLosSupermercados() {
        return (List<Supermercado>) repositorio.findAll();
    }

    @Override
    public Supermercado guardarSupermercado(Supermercado supermercado) {
        return repositorio.save(supermercado);
    }

    @Override
    public Supermercado obtenerSupermercadoPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Supermercado actualizarSupermercado(Supermercado supermercado) {
        return repositorio.save(supermercado);
    }

    @Override
    public void eliminarSupermercado(Long id) {
        repositorio.deleteById(id);
    }
    
}
