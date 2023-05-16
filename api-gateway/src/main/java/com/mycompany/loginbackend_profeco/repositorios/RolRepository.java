package com.mycompany.loginbackend_profeco.repositorios;


/**
 *
 * @author Jarol
 */

import com.mycompany.loginbackend_profeco.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {
}
