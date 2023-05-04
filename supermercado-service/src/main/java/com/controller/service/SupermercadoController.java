package com.controller.service;

import colas.supermercados.SuperMercadoCola;
import entidades.oficial.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/supermercado")
public class SupermercadoController {

    @GetMapping
    public ResponseEntity<List<Supermercados>> listarSupermercados() {
        try {
            SuperMercadoCola superMercadoCola = new SuperMercadoCola();
            List<Supermercados> lista = new ArrayList<>();
            try {
                Supermercados[] consumidores = superMercadoCola.listar();
                for (int i = 0; i < consumidores.length; i++) {
                    Supermercados consumidore = consumidores[i];
                    lista.add(consumidore);
                }
            } catch (Exception e) {
            }
            if (!lista.isEmpty()) {
                return ResponseEntity.ok(lista);
            }
        } catch (IOException ex) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (TimeoutException ex) {
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
                return ResponseEntity.ok(supermercado);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supermercados> actualizarSupermercado(@PathVariable int id, @RequestBody Supermercados supermercado) {
       try {
                SuperMercadoCola consumidorCola = new SuperMercadoCola();
            boolean agregado = consumidorCola.actualizar(supermercado);
            if (agregado) {
                return ResponseEntity.ok(supermercado);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supermercados> eliminarSupermercado(@PathVariable int id) {
      
       Supermercados consumidorOptional = null;
        try {
                SuperMercadoCola consumidorCola = new SuperMercadoCola();
            consumidorOptional = consumidorCola.obtenerID(id);
        } catch (Exception e) {
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
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supermercados> encontrarSupermercadoPorId(@PathVariable int id) {

        Supermercados consumidorOptional = null;
        try {
            SuperMercadoCola consumidorCola = new SuperMercadoCola();
            consumidorOptional = consumidorCola.obtenerID(id);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (consumidorOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            return ResponseEntity.ok(consumidorOptional);
        }

    
    }

}
