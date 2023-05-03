package datosinterfaces;

import entidades_consumidor.Wishlist;
import entidades_supermercados.Productos;
import java.util.List;

public interface IProductos {
    
    public List<Productos> listarTodasLasProductos();

    public boolean guardarProductos(Productos wishlist);

    public Productos obtenerProductosPorId(int id);

    public boolean actualizarProductos(Productos wishlist);

    public boolean eliminarProductos(int id);
    
}
