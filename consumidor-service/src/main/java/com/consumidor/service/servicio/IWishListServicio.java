package com.consumidor.service.servicio;

import com.consumidorservice.model.WishList;
import java.util.List;

public interface IWishListServicio {
    
    public List<WishList> listarTodasLasWishList();

    public WishList guardarWishList(WishList wishlist);

    public WishList obtenerWishListPorId(Long id);

    public WishList actualizarWishList(WishList wishlist);

    public void eliminarWishList(Long id);
    
}
