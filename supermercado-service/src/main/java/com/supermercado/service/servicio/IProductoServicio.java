package com.supermercado.service.servicio;

import com.supermercadoservice.model.Producto;
import java.util.List;

public interface IProductoServicio {
    public List<Producto> listarTodosLosProductos();

    public Producto guardarProducto(Producto producto);

    public Producto obtenerProductoPorId(Long id);

    public Producto actualizarProducto(Producto supermercado);

    public void eliminarProducto(Long id);
    
}
