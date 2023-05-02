package com.mycompany.loginbackend_profeco.repositorios;


import com.mycompany.loginbackend_profeco.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jarol
 */

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByUsername(String username);

}
