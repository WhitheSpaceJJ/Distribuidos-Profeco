package com.controller.service;

import com.supermercado.service.servicio.ISupermercadoServicio;
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
@RequestMapping("/supermercado")
public class SupermercadoController {

    @Autowired
    private ISupermercadoServicio supermercadoServicio;


    @GetMapping
    public ResponseEntity<List<Supermercado>> listarSupermercados() {
        List<Supermercado> supermercados = supermercadoServicio.listarTodosLosSupermercados();
        return ResponseEntity.ok(supermercados);
    }

    @PostMapping
    public ResponseEntity<Supermercado> agregarSupermercado(@RequestBody Supermercado supermercado) {
        Supermercado supermercadoGuardado = supermercadoServicio.guardarSupermercado(supermercado);

        return ResponseEntity.ok(supermercadoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supermercado> actualizarSupermercado(@PathVariable Long id, @RequestBody Supermercado supermercado) {
        Supermercado supermercadoOptional = supermercadoServicio.obtenerSupermercadoPorId(id);

        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        supermercado.setId(supermercadoOptional.getId());
        supermercadoServicio.guardarSupermercado(supermercado);

        return ResponseEntity.ok(supermercado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supermercado> eliminarSupermercado(@PathVariable Long id) {
        Supermercado supermercadoOptional = supermercadoServicio.obtenerSupermercadoPorId(id);

        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        supermercadoServicio.eliminarSupermercado(id);
        return ResponseEntity.ok(supermercadoOptional);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supermercado> encontrarSupermercadoPorId(@PathVariable Long id) {
        Supermercado supermercadoOptional = supermercadoServicio.obtenerSupermercadoPorId(id);
        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(supermercadoOptional);
    }

}
