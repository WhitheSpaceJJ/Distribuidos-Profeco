package com.controller.service;

import entidades.oficial.Supermercadosfavoritos;
import colas.consumidor.SuperMercadoFavoritoCola;
import com.consumidor.service.servicio.IServicioIntegracion;
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
@RequestMapping("/consumidor/spfavorito")
public class SupermercadoFavoritoController {

    @Autowired
    private IServicioIntegracion servicioIntegracion;

    @GetMapping
    public ResponseEntity<List<Supermercadosfavoritos>> listarSupermercadosFavoritos() {
        try {
            SuperMercadoFavoritoCola superMercadoFavoritoCola = new SuperMercadoFavoritoCola();
            Supermercadosfavoritos[] supermercadosfavoritoses = null;
            List<Supermercadosfavoritos> supermercadosfavoritoses1 = new ArrayList<>();
            supermercadosfavoritoses = superMercadoFavoritoCola.listar();
            for (int i = 0; i < supermercadosfavoritoses.length; i++) {
                Supermercadosfavoritos supermercadosfavoritose = supermercadosfavoritoses[i];
                supermercadosfavoritoses1.add(supermercadosfavoritose);
            }
            
            return ResponseEntity.ok(supermercadosfavoritoses1);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    /**
     * Agrega un supermercado favorito
     *
     * @param spFavorito Supermercado favorito a agregar.
     * @return El supermercado favorito a guardar.
     */
    @PostMapping
    public ResponseEntity<Supermercadosfavoritos> agregarSupermercadoFavorito(@RequestBody Supermercadosfavoritos spFavorito) {
        try {
            if (servicioIntegracion.supermercadoExists(spFavorito.getSupermercadoId())) {
                try {
                    SuperMercadoFavoritoCola superMercadoFavoritoCola = new SuperMercadoFavoritoCola();
                    boolean agregado = superMercadoFavoritoCola.guardar(spFavorito);
                    if (agregado) {
                        Supermercadosfavoritos[] array = superMercadoFavoritoCola.listar();
                        spFavorito.setIdFavoritos(array[array.length-1].getIdFavoritos());
                        
                        return ResponseEntity.ok(spFavorito);
                    } else {
                        return ResponseEntity.unprocessableEntity().build();
                    }
                } catch (Exception e) {
                    return ResponseEntity.unprocessableEntity().build();
                }
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Supermercadosfavoritos> actualizarSupermercadoFavorito(@PathVariable int id, @RequestBody Supermercadosfavoritos spFavorito) {
//        Supermercadosfavoritos spFavoritoOptional = null;
//        SuperMercadoFavoritoCola superMercadoFavoritoCola = null;
//        try {
//            superMercadoFavoritoCola = new SuperMercadoFavoritoCola();
//        } catch (IOException ex) {
//            return ResponseEntity.unprocessableEntity().build();
//        } catch (TimeoutException ex) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        try {
//            spFavoritoOptional = superMercadoFavoritoCola.obtenerID(id);
//
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        if (spFavoritoOptional == null) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        
//        try {
//            spFavorito.setIdFavoritos(id);
//            boolean actualizado = superMercadoFavoritoCola.actualizar(spFavorito);
//            if (actualizado) {
//                return ResponseEntity.ok(spFavorito);
//            } else {
//                return ResponseEntity.unprocessableEntity().build();
//            }
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supermercadosfavoritos> eliminarSupermercadoFavorito(@PathVariable int id) {
        Supermercadosfavoritos spFavoritoOptional = null;
        SuperMercadoFavoritoCola superMercadoFavoritoCola = null;
        try {
            superMercadoFavoritoCola = new SuperMercadoFavoritoCola();
        } catch (IOException ex) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (TimeoutException ex) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            spFavoritoOptional = superMercadoFavoritoCola.obtenerID(id);

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (spFavoritoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        try {
            boolean actualizado = superMercadoFavoritoCola.eliminar(id);
            if (actualizado) {
                return ResponseEntity.ok(spFavoritoOptional);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Supermercadosfavoritos> encontrarSupermercadoFavoritoPorId(@PathVariable int id) {
//        Supermercadosfavoritos spFavoritoOptional = null;
//        SuperMercadoFavoritoCola superMercadoFavoritoCola = null;
//        try {
//            superMercadoFavoritoCola = new SuperMercadoFavoritoCola();
//        } catch (IOException ex) {
//            return ResponseEntity.unprocessableEntity().build();
//        } catch (TimeoutException ex) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        try {
//            spFavoritoOptional = superMercadoFavoritoCola.obtenerID(id);
//
//        } catch (Exception e) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        if (spFavoritoOptional == null) {
//            return ResponseEntity.unprocessableEntity().build();
//        }else{
//        return ResponseEntity.ok(spFavoritoOptional);
//        }
//    }
}
