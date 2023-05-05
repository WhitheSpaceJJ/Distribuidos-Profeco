package com.controller.service;

import colas.profeco.service.ProfecoCola;
import entidades.oficial.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/profeco")
public class ProfecoController {

    @GetMapping
    public ResponseEntity<List<Profeco>> listarProfeco() {
        try {
            ProfecoCola profecoCola = new ProfecoCola();
            List<Profeco> lista = new ArrayList<>();
            try {
                Profeco[] profecoArray = profecoCola.listar();
                for (int i = 0; i < profecoArray.length; i++) {
                    Profeco profeco = profecoArray[i];
                    lista.add(profeco);
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
    public ResponseEntity<Profeco> agregarProfeco(@RequestBody Profeco profeco) {
        try {
            ProfecoCola profecoCola = new ProfecoCola();
            boolean agregado = profecoCola.guardar(profeco);
            if (agregado) {
                return ResponseEntity.ok(profeco);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profeco> actualizarProfeco(@PathVariable int id, @RequestBody Profeco profeco) {
        try {
            ProfecoCola profecoCola = new ProfecoCola();
            boolean agregado = profecoCola.actualizar(profeco);
            if (agregado) {
                return ResponseEntity.ok(profeco);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profeco> eliminarProfeco(@PathVariable int id) {

        Profeco profecoOptional = null;
        try {
            ProfecoCola profecoCola = new ProfecoCola();
            profecoOptional = profecoCola.obtenerID(id);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (profecoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            ProfecoCola profecoCola = new ProfecoCola();

            boolean agregado = profecoCola.eliminar(id);
            if (agregado) {
                return ResponseEntity.ok(profecoOptional);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profeco> encontrarProfecoPorId(@PathVariable int id) {

        Profeco profecoOptional = null;
        try {
            ProfecoCola profecoCola = new ProfecoCola();
            profecoOptional = profecoCola.obtenerID(id);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (profecoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            return ResponseEntity.ok(profecoOptional);
        }

    }

}
