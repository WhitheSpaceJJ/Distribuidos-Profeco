package com.supermercado.service.servicio;

import com.supermercadoservice.model.Comentario;
import java.util.List;

public interface IComentarioServicio {
    public List<Comentario> listarTodosLosComentarios();

    public Comentario guardarComentario(Comentario comentario);

    public Comentario obtenerComentarioPorId(Long id);

    public Comentario actualizarComentario(Comentario comentario);

    public void eliminarComentario(Long id);
}
