package com.controller.service;

import com.consumidor.service.servicio.IConsumidorServicio;
import com.consumidorservice.model.Consumidor;
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
@RequestMapping("/consumidor")
public class ConsumidorController {

    @Autowired
    private IConsumidorServicio consumidorServicio;


    @GetMapping
    public ResponseEntity<List<Consumidor>> listarConsumidores() {
        return ResponseEntity.ok(consumidorServicio.listarTodosLosConsumidores());
    }

    @PostMapping
    public ResponseEntity<Consumidor> agregarConsumidor(@RequestBody Consumidor consumidor) {
        return ResponseEntity.ok(consumidorServicio.guardarConsumidor(consumidor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consumidor> actualizarConsumidor(@PathVariable Long id, @RequestBody Consumidor consumidor) {
        Consumidor consumidorOptional = consumidorServicio.obtenerConsumidorPorId(id);

        if (consumidorOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        consumidor.setId_consumidores(consumidorOptional.getId_consumidores());
        consumidorServicio.guardarConsumidor(consumidor);

        return ResponseEntity.ok(consumidor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Consumidor> eliminarConsumidor(@PathVariable Long id) {
        Consumidor consumidorOptional = consumidorServicio.obtenerConsumidorPorId(id);

        if (consumidorOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        consumidorServicio.eliminarConsumidor(id);
        return ResponseEntity.ok(consumidorOptional);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consumidor> encontrarConsumidorPorId(@PathVariable Long id) {
        Consumidor consumidorOptional = consumidorServicio.obtenerConsumidorPorId(id);
        if (consumidorOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(consumidorOptional);
    }

}
