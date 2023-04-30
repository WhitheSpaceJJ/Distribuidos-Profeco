package com.consumidor.service.servicio;

import com.consumidorservice.model.SupermercadoFavorito;
import com.consumidorservice.repository.ISupermercadoFavoritoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupermercadoFavoritoServicio implements ISupermercadoFavoritoServicio {

    @Autowired
    private ISupermercadoFavoritoRepository repositorio;
    
    @Override
    public List<SupermercadoFavorito> listarTodosLosSupermercadosFavoritos() {
        return (List<SupermercadoFavorito>) repositorio.findAll();
    }

    @Override
    public SupermercadoFavorito guardarSupermercadoFavorito(SupermercadoFavorito supermercadoFavorito) {
        return repositorio.save(supermercadoFavorito);
    }

    @Override
    public SupermercadoFavorito obtenerSupermercadoFavoritoPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public SupermercadoFavorito actualizarSupermercadoFavorito(SupermercadoFavorito supermercadoFavorito) {
        return repositorio.save(supermercadoFavorito);
    }

    @Override
    public void eliminarSupermercadoFavorito(Long id) {
        repositorio.deleteById(id);
    }

}
