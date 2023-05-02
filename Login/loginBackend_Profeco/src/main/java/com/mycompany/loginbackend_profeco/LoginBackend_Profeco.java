/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginbackend_profeco;



import com.mycompany.loginbackend_profeco.modelo.Rol;
import com.mycompany.loginbackend_profeco.modelo.Usuario;
import com.mycompany.loginbackend_profeco.modelo.UsuarioRol;
import com.mycompany.loginbackend_profeco.servicios.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Jarol
 */

@SpringBootApplication
public class LoginBackend_Profeco implements CommandLineRunner{

   @Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(LoginBackend_Profeco.class, args);
	}
        
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        
        // crean una bd con el nombre de "usuarioslogin" y  eliminar los comentarios de lo que esta en el run y corrrer
        //para crear las tablas de la bd 
	@Override
	public void run(String... args) throws Exception {
		/*
			Usuario usuario = new Usuario();

			usuario.setNombre("Christian");
			usuario.setApellido("Ramirez");
			usuario.setUsername("christian");
			usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
			usuario.setEmail("alex@gmail.com");
			usuario.setTelefono("988212020");
			usuario.setPerfil("foto.png");

			Rol rol = new Rol();
			rol.setRolId(1L);
			rol.setRolNombre("ADMIN");

			Set<UsuarioRol> usuariosRoles = new HashSet<>();
			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setRol(rol);
			usuarioRol.setUsuario(usuario);
			usuariosRoles.add(usuarioRol);

			Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario,usuariosRoles);
			System.out.println(usuarioGuardado.getUsername());*/
        }
		
}
