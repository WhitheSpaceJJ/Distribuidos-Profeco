package com.controller.service;

import colas.consumidor.WhishListCola;
import com.consumidor.service.servicio.IServicioIntegracion;
import entidades.oficial.*;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/consumidor/wishlist")
public class WishListController {

    // Servicio de integración de microservicios
    @Autowired
    private IServicioIntegracion servicioIntegracion;

    @GetMapping
    public ResponseEntity<List<Wishlist>> listarWishList() {
        try {
            WhishListCola whishListCola = new WhishListCola();
            Wishlist[] whiWishlists = whishListCola.listar();
            List<Wishlist> lista = new ArrayList<>();
            for (int i = 0; i < whiWishlists.length; i++) {
                Wishlist whiWishlist = whiWishlists[i];
                lista.add(whiWishlist);
            }
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PostMapping
    public ResponseEntity<Wishlist> agregarWishList(@RequestBody Wishlist wishList) {
        try {
            if (servicioIntegracion.supermercadoExists(wishList.getSupermercadoId())) {
                try {
                    WhishListCola whishListCola = new WhishListCola();
                    if (whishListCola.guardar(wishList)) {
                        return ResponseEntity.ok(wishList);
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

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> actualizarWishList(@PathVariable int id, @RequestBody Wishlist wishList) {
        try {
            WhishListCola whishListCola = null;
            Wishlist wishListOptional = null;
            try {
                whishListCola = new WhishListCola();
                wishListOptional = whishListCola.obtenerID(id);
            } catch (IOException | TimeoutException | InterruptedException | ExecutionException ex) {
                return ResponseEntity.unprocessableEntity().build();
            }

            if (wishListOptional == null) {
                return ResponseEntity.unprocessableEntity().build();
            }

            if (whishListCola.actualizar(wishListOptional)) {
                return ResponseEntity.ok(wishListOptional);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (IOException | InterruptedException | ExecutionException ex) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wishlist> eliminarWishList(@PathVariable int id) {
        try {
            WhishListCola whishListCola = null;
            Wishlist wishListOptional = null;
            try {
                whishListCola = new WhishListCola();
                wishListOptional = whishListCola.obtenerID(id);
            } catch (IOException | TimeoutException | InterruptedException | ExecutionException ex) {
                return ResponseEntity.unprocessableEntity().build();
            }

            if (wishListOptional == null) {
                return ResponseEntity.unprocessableEntity().build();
            }

            if (whishListCola.eliminar(id)) {
                return ResponseEntity.ok(wishListOptional);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (IOException | InterruptedException | ExecutionException ex) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> encontrarWishListPorId(@PathVariable int id) {
        WhishListCola whishListCola = null;
        Wishlist wishListOptional = null;
        try {
            whishListCola = new WhishListCola();
            wishListOptional = whishListCola.obtenerID(id);
        } catch (IOException | TimeoutException | InterruptedException | ExecutionException ex) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (wishListOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(wishListOptional);
    }

}
