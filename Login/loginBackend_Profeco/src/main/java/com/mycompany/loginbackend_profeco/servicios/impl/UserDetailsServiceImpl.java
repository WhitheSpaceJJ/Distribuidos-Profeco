package com.mycompany.loginbackend_profeco.servicios.impl;


import com.mycompany.loginbackend_profeco.modelo.Usuario;
import com.mycompany.loginbackend_profeco.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 *
 * @author Jarol
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 // esta clase permite buscar un usuario por el user name
    @Autowired
    private UsuarioRepository usuarioRepository;

   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }

}
