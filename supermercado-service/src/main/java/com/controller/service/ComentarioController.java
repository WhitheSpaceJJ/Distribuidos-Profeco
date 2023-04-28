package com.controller.service;

import com.supermercado.service.servicio.IComentarioServicio;
import com.supermercado.service.servicio.ISupermercadoServicio;
import com.supermercadoservice.model.Comentario;
import com.supermercadoservice.model.Supermercado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supermercado/comentario")
public class ComentarioController {

    @Autowired
    private IComentarioServicio comentarioService;

    @Autowired
    private ISupermercadoServicio supermercadoServicio;

    @PostMapping
    public ResponseEntity<Comentario> agregarComentario(@RequestBody Comentario comentario) {

        Supermercado supermercadoOptional = supermercadoServicio.obtenerSupermercadoPorId(comentario.getSupermercado().getId());

        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        comentario.setSupermercado(supermercadoOptional);

        Comentario comentarioGuardado = comentarioService.guardarComentario(comentario);


        return ResponseEntity.ok(comentarioGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        Supermercado supermercadoOptional = supermercadoServicio.obtenerSupermercadoPorId(comentario.getSupermercado().getId());

        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Comentario comentarioGuardado = comentarioService.obtenerComentarioPorId(id);

        if (comentarioGuardado == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        comentario.setId(comentarioGuardado.getId());
        comentario.setSupermercado(supermercadoOptional);
        comentarioService.guardarComentario(comentario);

        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comentario> eliminarComentario(@PathVariable Long id) {
        Comentario comentarioGuardado = comentarioService.obtenerComentarioPorId(id);

        if (comentarioGuardado == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        comentarioService.eliminarComentario(comentarioGuardado.getId());
        return ResponseEntity.ok(comentarioGuardado);
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentarios() {
        return ResponseEntity.ok(comentarioService.listarTodosLosComentarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Long id) {
        Comentario comentarioGuardado = comentarioService.obtenerComentarioPorId(id);
        if (comentarioGuardado == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(comentarioGuardado);
    }

}
