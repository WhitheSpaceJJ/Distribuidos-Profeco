package com.supermercado.service.servicio;

import com.supermercadoservice.model.Comentario;
import com.supermercadoservice.repository.IComentarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComentarioService implements IComentarioServicio{
    
    @Autowired
    private IComentarioRepository repositorio;
    
    @Override
    public List<Comentario> listarTodosLosComentarios() {
        return repositorio.findAll();
    }

    @Override
    public Comentario guardarComentario(Comentario comentario) {
        return repositorio.save(comentario);
    }

    @Override
    public Comentario obtenerComentarioPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Comentario actualizarComentario(Comentario comentario) {
        return repositorio.save(comentario);
    }

    @Override
    public void eliminarComentario(Long id) {
        repositorio.deleteById(id);
    }
}
