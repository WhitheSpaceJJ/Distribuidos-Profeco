package com.mycompany.loginbackend_profeco.controladores;

import com.mycompany.loginbackend_profeco.configuraciones.JwtUtils;
import com.mycompany.loginbackend_profeco.modelo.JwtRequest;
import com.mycompany.loginbackend_profeco.modelo.JwtResponse;
import com.mycompany.loginbackend_profeco.modelo.Usuario;
import com.mycompany.loginbackend_profeco.servicios.impl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 *
 * @author Jarol
 */
@RestController
@CrossOrigin("*")
public class AuthenticationController {
//se inyectan estas 

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;
//para generar el token

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (Exception exception) {
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        //se genra el token pasandole los datos del usuario
        String token = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    // verifica que este correcto y va a dar permiso es decir va a autenticar
    private void autenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException exception) {
            throw new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

    // devuelve es el que acaba de iniciar sesion es decir el actual
    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal) {
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
