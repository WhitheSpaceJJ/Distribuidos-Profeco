package com.app.web.repositorio;

import com.app.web.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Long> {
}
