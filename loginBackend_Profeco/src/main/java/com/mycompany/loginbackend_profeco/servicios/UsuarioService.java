package com.mycompany.loginbackend_profeco.servicios;



import com.mycompany.loginbackend_profeco.modelo.Usuario;
import com.mycompany.loginbackend_profeco.modelo.UsuarioRol;
import java.util.Set;

/**
 *
 * @author Jarol
 */

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);
}
