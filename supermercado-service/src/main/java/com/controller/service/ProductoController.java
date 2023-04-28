package com.controller.service;

import com.supermercado.service.servicio.IProductoServicio;
import com.supermercado.service.servicio.ISupermercadoServicio;
import com.supermercadoservice.model.Producto;
import com.supermercadoservice.model.Supermercado;
import java.net.URI;
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
@RequestMapping("/supermercado/producto")
public class ProductoController {

    @Autowired
    private IProductoServicio productoServicio;
    
    @Autowired
    private ISupermercadoServicio supermercadoServicio;

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Supermercado supermercadoOptional = supermercadoServicio.obtenerSupermercadoPorId(producto.getSupermercado().getId());
        
        if(supermercadoOptional == null){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        producto.setSupermercado(supermercadoOptional);
        Producto productoGuardado = productoServicio.guardarProducto(producto);

        

        return ResponseEntity.ok(productoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Supermercado supermercadoOptional = supermercadoServicio.obtenerSupermercadoPorId(producto.getSupermercado().getId());
        
        if(supermercadoOptional == null){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        Producto productoGuardado = productoServicio.obtenerProductoPorId(id);

        if (productoGuardado == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        
        producto.setSupermercado(supermercadoOptional);
        producto.setId(productoGuardado.getId());
        productoServicio.guardarProducto(producto);

        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable Long id) {
        Producto productoOptional = productoServicio.obtenerProductoPorId(id);

        if (productoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        productoServicio.eliminarProducto(productoOptional.getId());
        return ResponseEntity.ok(productoOptional);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoServicio.listarTodosLosProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto productoOptional = productoServicio.obtenerProductoPorId(id);
        if (productoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(productoOptional);
    }

}
