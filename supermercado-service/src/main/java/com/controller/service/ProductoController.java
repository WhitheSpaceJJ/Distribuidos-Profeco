package com.controller.service;

import colas.supermercados.ProductoCola;
import colas.supermercados.SuperMercadoCola;
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
@RequestMapping("/supermercado/producto")
public class ProductoController {

    @PostMapping
    public ResponseEntity<Productos> agregarProducto(@RequestBody Productos producto) {
        Supermercados supermercadoOptional = null;

        try {
            SuperMercadoCola supermercadoCola = new SuperMercadoCola();
            supermercadoOptional = supermercadoCola.obtenerID(producto.getSupermercadoId().getIdSupermercados());
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        
        try {
            ProductoCola productoCola = new ProductoCola();
            producto.setSupermercadoId(supermercadoOptional);
            boolean agregado = productoCola.guardar(producto);
            if (agregado) {
                 ProductoCola consumidorCola = new ProductoCola();
            List<Productos> lista = new ArrayList<>();
            try {
                Productos[] consumidores = consumidorCola.listar();
                for (Productos consumidore : consumidores) {
                    lista.add(consumidore);
                }
            } catch (IOException | InterruptedException | ExecutionException e) {
            }
            if (!lista.isEmpty()) {
                producto.setIdProductos(lista.get(lista.size()-1).getIdProductos());
            }
            Productos productoObtener=producto;
                return ResponseEntity.ok(productoObtener);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable int id, @RequestBody Productos producto) {
        Supermercados supermercadoOptional = null;

        try {
            SuperMercadoCola consumidorCola = new SuperMercadoCola();
            supermercadoOptional = consumidorCola.obtenerID(producto.getSupermercadoId().getIdSupermercados());
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (supermercadoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        
        try {
            ProductoCola consumidorCola = new ProductoCola();
            producto.setIdProductos(id);
            
            boolean agregado = consumidorCola.actualizar(producto);
            if (agregado) {
                producto.setSupermercadoId(supermercadoOptional);
                return ResponseEntity.ok(producto);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Productos> eliminarProducto(@PathVariable int id) {
        Productos consumidorOptional = null;
        try {
            ProductoCola consumidorCola = new ProductoCola();
            consumidorOptional = consumidorCola.obtenerID(id);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (consumidorOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            ProductoCola consumidorCola = new ProductoCola();
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
    public ResponseEntity<List<Productos>> listarProductos() {

        try {
            ProductoCola consumidorCola = new ProductoCola();
            List<Productos> lista = new ArrayList<>();
            try {
                Productos[] consumidores = consumidorCola.listar();
                for (Productos consumidore : consumidores) {
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
    public ResponseEntity<Productos> obtenerProductoPorId(@PathVariable int id) {
        Productos consumidorOptional = null;
        try {
            ProductoCola consumidorCola = new ProductoCola();
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
