package com.controller.service;

import colas.profeco.service.MultaCola;
import colas.profeco.service.SuperMercadoCola;
import entidades.oficial.*;

import java.io.IOException;
import java.util.ArrayList;
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
@RequestMapping("/profeco/multa")
public class MultaController {

    @PostMapping
    public ResponseEntity<Multa> agregarMulta(@RequestBody Multa multa) {
        Supermercados supermercadoOptional = null;

        try {
            SuperMercadoCola supermercadoCola = new SuperMercadoCola();
            supermercadoOptional = supermercadoCola.obtenerID(multa.getSupermercadoId().getIdSupermercados());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            MultaCola multaCola = new MultaCola();
            boolean agregado = multaCola.guardar(multa);
            if (agregado) {
                multa.setSupermercadoId(supermercadoOptional);
                return ResponseEntity.ok(multa);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Multa> actualizarMulta(@PathVariable int id, @RequestBody Multa multa) {
        Supermercados supermercadoOptional = null;

        try {
            SuperMercadoCola supermercadoCola = new SuperMercadoCola();
            supermercadoOptional = supermercadoCola.obtenerID(id);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            MultaCola multaCola = new MultaCola();
            boolean agregado = multaCola.actualizar(multa);
            if (agregado) {
                multa.setSupermercadoId(supermercadoOptional);
                return ResponseEntity.ok(multa);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Multa> eliminarMulta(@PathVariable int id) {
        Multa multaOptional = null;
        try {
            MultaCola multaCola = new MultaCola();
            multaOptional = multaCola.obtenerID(id);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (multaOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            MultaCola multaCola = new MultaCola();
            boolean eliminado = multaCola.eliminar(id);
            if (eliminado) {
                return ResponseEntity.ok(multaOptional);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Multa>> listarMultas() {

        try {
            MultaCola multaCola = new MultaCola();
            List<Multa> lista = new ArrayList<>();
            try {
                Multa[] multas = multaCola.listar();
                for (Multa multa : multas) {
                    lista.add(multa);
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
    public ResponseEntity<Multa> obtenerMultaPorId(@PathVariable int id) {
        Multa multaOptional = null;
        try {
            MultaCola multaCola = new MultaCola();
            multaOptional = multaCola.obtenerID(id);
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (multaOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            return ResponseEntity.ok(multaOptional);
        }
    }

}
