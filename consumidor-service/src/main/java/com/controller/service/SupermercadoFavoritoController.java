package com.controller.service;

import com.consumidor.service.servicio.IServicioIntegracion;
import com.consumidor.service.servicio.ISupermercadoFavoritoServicio;
import com.consumidorservice.model.SupermercadoFavorito;
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
@RequestMapping("/consumidor/spfavorito")
public class SupermercadoFavoritoController {

    @Autowired
    private ISupermercadoFavoritoServicio spFavoritoServicio;

    @Autowired
    private IServicioIntegracion servicioIntegracion;

    @GetMapping
    public ResponseEntity<List<SupermercadoFavorito>> listarSupermercadosFavoritos() {
        return ResponseEntity.ok(spFavoritoServicio.listarTodosLosSupermercadosFavoritos());
    }

    /**
     * Agrega un supermercado favorito
     *
     * @param spFavorito Supermercado favorito a agregar.
     * @return El supermercado favorito a guardar.
     */
    @PostMapping
    public ResponseEntity<SupermercadoFavorito> agregarSupermercadoFavorito(@RequestBody SupermercadoFavorito spFavorito) {
        try {
            if (servicioIntegracion.supermercadoExists(spFavorito.getSupermercado_id())) {
                return ResponseEntity.ok(spFavoritoServicio.guardarSupermercadoFavorito(spFavorito));
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupermercadoFavorito> actualizarSupermercadoFavorito(@PathVariable Long id, @RequestBody SupermercadoFavorito spFavorito) {
        SupermercadoFavorito spFavoritoOptional = spFavoritoServicio.obtenerSupermercadoFavoritoPorId(id);

        if (spFavoritoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        spFavorito.setId_favoritos(spFavoritoOptional.getId_favoritos());
        spFavoritoServicio.guardarSupermercadoFavorito(spFavorito);

        return ResponseEntity.ok(spFavorito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupermercadoFavorito> eliminarSupermercadoFavorito(@PathVariable Long id) {
        SupermercadoFavorito spFavoritoOptional = spFavoritoServicio.obtenerSupermercadoFavoritoPorId(id);

        if (spFavoritoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        spFavoritoServicio.eliminarSupermercadoFavorito(id);
        return ResponseEntity.ok(spFavoritoOptional);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupermercadoFavorito> encontrarSupermercadoFavoritoPorId(@PathVariable Long id) {
        SupermercadoFavorito spFavoritoOptional = spFavoritoServicio.obtenerSupermercadoFavoritoPorId(id);
        if (spFavoritoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(spFavoritoOptional);
    }

}
