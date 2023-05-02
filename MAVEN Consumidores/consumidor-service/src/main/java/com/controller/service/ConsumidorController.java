package com.controller.service;

import colas.consumidor.ConsumidorCola;
import entidades.entidades_consumidor.Consumidores;
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
@RequestMapping("/consumidor")
public class ConsumidorController {

    @GetMapping
    public ResponseEntity<List<Consumidores>> listarConsumidores() {
        try {
            ConsumidorCola consumidorCola = new ConsumidorCola();
            List<Consumidores> lista = new ArrayList<>();
            try {
                Consumidores[] consumidores = consumidorCola.listar();
                for (int i = 0; i < consumidores.length; i++) {
                    Consumidores consumidore = consumidores[i];
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
    public ResponseEntity<Consumidores> agregarConsumidor(@RequestBody Consumidores consumidor) {
        try {
            ConsumidorCola consumidorCola = new ConsumidorCola();
            boolean agregado = consumidorCola.guardar(consumidor);
            if (agregado) {
                return ResponseEntity.ok(consumidor);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consumidores> actualizarConsumidor(@PathVariable Long id, @RequestBody Consumidores consumidor) {
        try {
            ConsumidorCola consumidorCola = new ConsumidorCola();
            boolean agregado = consumidorCola.actualizar(consumidor);
            if (agregado) {
                return ResponseEntity.ok(consumidor);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Consumidores> eliminarConsumidor(@PathVariable int id) {
        Consumidores consumidorOptional = null;
        try {
            ConsumidorCola consumidorCola = new ConsumidorCola();
            consumidorOptional = consumidorCola.obtenerID(id);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (consumidorOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            ConsumidorCola consumidorCola = new ConsumidorCola();
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
    public ResponseEntity<Consumidores> encontrarConsumidorPorId(@PathVariable int id) {
        Consumidores consumidorOptional = null;
        try {
            ConsumidorCola consumidorCola = new ConsumidorCola();
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
