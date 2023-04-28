package com.supermercado.service.servicio;

import com.supermercadoservice.model.Producto;
import com.supermercadoservice.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio implements IProductoServicio{
    
    @Autowired
    private IProductoRepository repositorio;
    
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
