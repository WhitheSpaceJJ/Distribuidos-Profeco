package com.app.web.servicio;

import com.app.web.entidad.Producto;
import com.app.web.repositorio.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private IProductoRepositorio repositorio;

    @Override
    public List<Producto> listarTodosLosProductos() {
        return repositorio.findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return repositorio.save(producto);
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return repositorio.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
         repositorio.deleteById(id);
    }
}
