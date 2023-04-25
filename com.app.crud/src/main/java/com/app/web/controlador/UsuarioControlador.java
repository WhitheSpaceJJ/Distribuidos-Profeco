package com.app.web.controlador;


import com.app.web.entidad.Usuario;
import com.app.web.servicio.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioControlador {

    @Autowired
    private IUsuarioServicio servicio;

    @GetMapping({"/usuarios","/"})
    public String listarUsuarios(Model modelo){
        modelo.addAttribute("usuarios", servicio.listarTodosLosUsuarios());
        return "usuarios"; //retorna al archivo usuarios
    }

    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioDeRgistrarUsuario(Model modelo){
        Usuario usuario = new Usuario();
        modelo.addAttribute("usuario", usuario);
        return "crear_usuario";
    }

    @PostMapping("/usuarios")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario){
        servicio.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo){
        modelo.addAttribute("usuario", servicio.obtenerUsuarioPorId(id));
        return "editar_usuario";
    }

    @PostMapping("/usuarios/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute("usuario") Usuario usuario, Model modelo){
        Usuario usuarioExistente = servicio.obtenerUsuarioPorId(id);
        usuarioExistente.setId(id);
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setContrasenha(usuario.getContrasenha());
        usuarioExistente.setTipo(usuario.getTipo());

        servicio.actualizarUsuario(usuarioExistente);
        return "redirect:/usuarios";

    }

    @GetMapping("/usuarios/{id}")
    public String eliminarUsuario(@PathVariable Long id){
        servicio.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

}
