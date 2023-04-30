package com.controller.service;

import com.consumidor.service.servicio.IWishListServicio;
import com.consumidorservice.model.WishList;
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
@RequestMapping("/consumidor/wishlist")
public class WishListController {

    @Autowired
    private IWishListServicio wishListServicio;

    @GetMapping
    public ResponseEntity<List<WishList>> listarWishList() {
        return ResponseEntity.ok(wishListServicio.listarTodasLasWishList());
    }

    @PostMapping
    public ResponseEntity<WishList> agregarWishList(@RequestBody WishList wishList) {
        return ResponseEntity.ok(wishListServicio.guardarWishList(wishList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishList> actualizarWishList(@PathVariable Long id, @RequestBody WishList wishList) {
        WishList wishListOptional = wishListServicio.obtenerWishListPorId(id);

        if (wishListOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        wishList.setId_wishlist(wishListOptional.getId_wishlist());
        wishListServicio.guardarWishList(wishList);

        return ResponseEntity.ok(wishList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WishList> eliminarWishList(@PathVariable Long id) {
        WishList wishListOptional = wishListServicio.obtenerWishListPorId(id);

        if (wishListOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        wishListServicio.eliminarWishList(id);
        return ResponseEntity.ok(wishListOptional);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishList> encontrarWishListPorId(@PathVariable Long id) {
        WishList wishListOptional = wishListServicio.obtenerWishListPorId(id);
        if (wishListOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(wishListOptional);
    }

}
