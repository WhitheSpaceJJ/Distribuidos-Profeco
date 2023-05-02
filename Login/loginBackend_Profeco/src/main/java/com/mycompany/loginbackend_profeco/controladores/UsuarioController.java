package com.mycompany.loginbackend_profeco.controladores;


import com.mycompany.loginbackend_profeco.modelo.Rol;
import com.mycompany.loginbackend_profeco.modelo.Usuario;
import com.mycompany.loginbackend_profeco.modelo.UsuarioRol;
import com.mycompany.loginbackend_profeco.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Jarol
 */

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
     @Autowired
     private BCryptPasswordEncoder bCryptPasswordEncoder ;

     
     // para guardar un usuario se tiene que colocar la urlbase localhost:8080 + /usuarios+/ user
    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");
        
        //encripta la clave del usuario que se esta mandando
        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
        
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        //por defecto  podria cambiarse para autorizar segun el rol(usuarioNormal,profeco o supermercado)
        rol.setRolNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }


    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

}
