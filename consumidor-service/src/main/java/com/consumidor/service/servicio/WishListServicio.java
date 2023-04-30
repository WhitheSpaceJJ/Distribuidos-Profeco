package com.consumidor.service.servicio;

import com.consumidorservice.model.WishList;
import com.consumidorservice.repository.IWishListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListServicio implements IWishListServicio {

    @Autowired
    private IWishListRepository repositorio;
    
    @Override
    public List<WishList> listarTodasLasWishList() {
        return repositorio.findAll();
    }

    @Override
    public WishList guardarWishList(WishList wishlist) {
        return repositorio.save(wishlist);
    }

    @Override
    public WishList obtenerWishListPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public WishList actualizarWishList(WishList wishlist) {
        return repositorio.save(wishlist);
    }

    @Override
    public void eliminarWishList(Long id) {
        repositorio.deleteById(id);
    }

}
