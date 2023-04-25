package com.app.web.servicio;

import com.app.web.entidad.Producto;

import java.util.List;

public interface IProductoServicio {

    public List<Producto> listarTodosLosProductos();

    public Producto guardarProducto(Producto producto);

    public Producto obtenerProductoPorId(Long id);

    public Producto actualizarProducto(Producto producto);

    public void eliminarProducto(Long id);
}
