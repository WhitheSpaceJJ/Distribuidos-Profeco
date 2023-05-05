package datosinterfaces;

import entidades_consumidor.Wishlist;
import java.util.List;

public interface IWishListServicio {
    
    public List<Wishlist> listarTodasLasWishList();

    public boolean guardarWishList(Wishlist wishlist);

    public Wishlist obtenerWishListPorId(int id);

    public boolean actualizarWishList(Wishlist wishlist);

    public boolean eliminarWishList(int id);
    
}
