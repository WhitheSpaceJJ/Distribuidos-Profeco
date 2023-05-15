package com.controller.service;

import colas.supermercados.ComentarioCola;
import colas.supermercados.SuperMercadoCola;
import com.supermercado.service.servicio.IServicioIntegracion;
import entidades.oficial.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
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
    private IServicioIntegracion servicioIntegracion;

    @PostMapping
    public ResponseEntity<Comentarios> agregarComentario(@RequestBody Comentarios comentario) {

        if (!servicioIntegracion.consumidorExists(comentario.getConsumidorId())) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Supermercados supermercadoOptional = null;

        try {
            SuperMercadoCola consumidorCola = new SuperMercadoCola();
            supermercadoOptional = consumidorCola.obtenerID(comentario.getSupermercadoId().getIdSupermercados());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            ComentarioCola consumidorCola = new ComentarioCola();
            boolean agregado = consumidorCola.guardar(comentario);
            if (agregado) {

                List<Comentarios> lista = new ArrayList<>();
                try {
                    Comentarios[] consumidores = consumidorCola.listar();
                    for (int i = 0; i < consumidores.length; i++) {
                        Comentarios consumidore = consumidores[i];
                        lista.add(consumidore);
                    }
                } catch (IOException | InterruptedException | ExecutionException e) {
                }
                if (!lista.isEmpty()) {
                    comentario.setIdComentarios(lista.get(lista.size() - 1).getIdComentarios());
                }
                Comentarios comentarioOficial = comentario;
                System.out.println(comentarioOficial.getIdComentarios());
                return ResponseEntity.ok(comentarioOficial);

            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentarios> actualizarComentario(@PathVariable int id, @RequestBody Comentarios comentario) {

        Supermercados supermercadoOptional = null;

        try {
            SuperMercadoCola consumidorCola = new SuperMercadoCola();
            supermercadoOptional = consumidorCola.obtenerID(comentario.getSupermercadoId().getIdSupermercados());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            ComentarioCola consumidorCola = new ComentarioCola();
            comentario.setIdComentarios(id);
            boolean agregado = consumidorCola.actualizar(comentario);
            if (agregado) {
                comentario.setSupermercadoId(supermercadoOptional);
                return ResponseEntity.ok(comentario);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comentarios> eliminarComentario(@PathVariable int id) {

        Comentarios consumidorOptional = null;
        try {
            ComentarioCola consumidorCola = new ComentarioCola();
            consumidorOptional = consumidorCola.obtenerID(id);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (consumidorOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            ComentarioCola consumidorCola = new ComentarioCola();
            boolean agregado = consumidorCola.eliminar(id);
            if (agregado) {
                return ResponseEntity.ok(consumidorOptional);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Comentarios>> listarComentarios() {

        try {
            ComentarioCola consumidorCola = new ComentarioCola();
            List<Comentarios> lista = new ArrayList<>();
            try {
                Comentarios[] consumidores = consumidorCola.listar();
                for (int i = 0; i < consumidores.length; i++) {
                    Comentarios consumidore = consumidores[i];
                    lista.add(consumidore);
                }
            } catch (IOException | InterruptedException | ExecutionException e) {
            }
            if (!lista.isEmpty()) {
                return ResponseEntity.ok(lista);
            }
        } catch (IOException | TimeoutException ex) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.unprocessableEntity().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentarios> obtenerComentarioPorId(@PathVariable int id) {
        Comentarios consumidorOptional = null;
        try {
            ComentarioCola consumidorCola = new ComentarioCola();
            consumidorOptional = consumidorCola.obtenerID(id);
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (consumidorOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            return ResponseEntity.ok(consumidorOptional);
        }
    }

}
