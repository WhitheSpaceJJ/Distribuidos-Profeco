package com.controller.service;

import colas.supermercados.SuperMercadoCola;
import entidades.oficial.Comentarios;
import entidades.oficial.Productos;
import entidades.oficial.Supermercados;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
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
@RequestMapping("/supermercado")
public class SupermercadoController {

    @GetMapping
    public ResponseEntity<List<Supermercados>> listarSupermercados() {
        try {
            SuperMercadoCola superMercadoCola = new SuperMercadoCola();
            List<Supermercados> lista = new ArrayList<>();
            try {
                Supermercados[] consumidores = superMercadoCola.listar();
                lista.addAll(Arrays.asList(consumidores));
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

    @PostMapping
    public ResponseEntity<Supermercados> agregarSupermercado(@RequestBody Supermercados supermercado) {
        try {
            SuperMercadoCola consumidorCola = new SuperMercadoCola();
            boolean agregado = consumidorCola.guardar(supermercado);
            if (agregado) {
                SuperMercadoCola superMercadoCola = new SuperMercadoCola();
                List<Supermercados> lista = new ArrayList<>();
                try {
                    Supermercados[] consumidores = superMercadoCola.listar();
                    lista.addAll(Arrays.asList(consumidores));
                } catch (IOException | InterruptedException | ExecutionException e) {
                }
                if (!lista.isEmpty()) {
                    supermercado.setIdSupermercados(lista.get(lista.size() - 1).getIdSupermercados());
                }
                Supermercados supermercadosObtener = supermercado;
                return ResponseEntity.ok(supermercadosObtener);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supermercados> actualizarSupermercado(@PathVariable int id, @RequestBody Supermercados supermercado) {
        try {
            SuperMercadoCola consumidorCola = new SuperMercadoCola();

            // Buscamos el supermercado
            Supermercados[] list = consumidorCola.listar();
            Supermercados supermercadoEncontrado = null;
            for (Supermercados sp : list) {
                if (sp.getIdSupermercados().equals(id)) {
                    supermercadoEncontrado = sp;
                }
            }
            List<Comentarios> comentarios = null;
            List<Productos> productos = null;
            if (supermercadoEncontrado != null) {
                // Actualizamos el supermercado
                comentarios = supermercadoEncontrado.getComentariosList();
                productos = supermercadoEncontrado.getProductosList();
                supermercadoEncontrado.setNombre(supermercado.getNombre());
                supermercadoEncontrado.setDireccion(supermercado.getDireccion());
                supermercadoEncontrado.setComentariosList(null);
                supermercadoEncontrado.setProductosList(null);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
            supermercadoEncontrado.setIdSupermercados(id);
            boolean agregado = consumidorCola.actualizar(supermercadoEncontrado);
            
            if (agregado) {
                supermercadoEncontrado.setComentariosList(comentarios);
                supermercadoEncontrado.setProductosList(productos);
                return ResponseEntity.ok(supermercadoEncontrado);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supermercados> eliminarSupermercado(@PathVariable int id) {

        Supermercados consumidorOptional = null;
        try {
            SuperMercadoCola consumidorCola = new SuperMercadoCola();
            consumidorOptional = consumidorCola.obtenerID(id);
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (consumidorOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            SuperMercadoCola consumidorCola = new SuperMercadoCola();

            boolean agregado = consumidorCola.eliminar(id);
            if (agregado) {
                return ResponseEntity.ok(consumidorOptional);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Supermercados> encontrarSupermercadoPorId(@PathVariable int id) {
//
//        Supermercados consumidorOptional = null;
//        try {
//            SuperMercadoCola consumidorCola = new SuperMercadoCola();
//            consumidorOptional = consumidorCola.obtenerID(id);
//        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        if (consumidorOptional == null) {
//            return ResponseEntity.unprocessableEntity().build();
//        } else {
//            return ResponseEntity.ok(consumidorOptional);
//        }
//
//    }

}
