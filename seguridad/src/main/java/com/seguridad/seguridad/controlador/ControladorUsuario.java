package com.seguridad.seguridad.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import com.seguridad.seguridad.modelo.Usuario;

import com.seguridad.seguridad.servicio.ServicioUsuario;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;






@Controller
public class ControladorUsuario {

    private final ServicioUsuario us;

    public ControladorUsuario(ServicioUsuario us) {
        this.us = us;
    }

    @GetMapping("/usuario")
    public String verVistaUsuario() {
        return "usuario";
    }

    @GetMapping("/usuario_agregar")
    public String agregarUsuario(Model modelo) {
        Usuario usuario = new Usuario();
        usuario.setAli_usu("");
        usuario.setEma_usu("");
        usuario.setCla_usu ("");
        usuario.setEst_usu ("");
        modelo.addAttribute("usuario", usuario);

        return "usuario_agregar";
    }



    @PostMapping("/usuario_agregar")
    public String agregarUsuario(@ModelAttribute ("usuario") Usuario usuario, RedirectAttributes redirectAttrs) {
        try {
            usuario.setAli_usu(usuario.getAli_usu() != null ? usuario.getAli_usu().trim() : "");
            usuario.setEma_usu(usuario.getEma_usu() != null ? usuario.getEma_usu().trim() : "");
            usuario.setCla_usu(usuario.getCla_usu() != null ? usuario.getCla_usu().trim() : "");
            usuario.setEst_usu(usuario.getEst_usu() != null ? usuario.getEst_usu().toUpperCase() : "A"); 
            us.agregarUsuario(usuario);
            redirectAttrs.addFlashAttribute( "exito", "!Usuario creado correctamente!");
            }
            catch (Exception e) {
                redirectAttrs.addFlashAttribute("error", "Error al crear el Usuario: " + e.getMessage());
            }
        return "redirect:/usuario_listar";
    }
    

    @GetMapping("/usuario_ver")
    public String buscarUsuario(@org.springframework.web.bind.annotation.RequestParam ("cod_usu") int cod_usu, Model modelo) {
        Usuario usuario = us.buscarUsuario(cod_usu);
        modelo.addAttribute("usuario", usuario);
        return "usuario_ver";
    }

    @GetMapping("/usuario_listar")
    public String listarUsuarios(Model modelo) {
        List<Usuario> usuarios = us.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_listar";
    }


    @GetMapping("/usuario_editar")
    public String editarUsuario(@org.springframework.web.bind.annotation.RequestParam ("cod_usu") int cod_usu, Model modelo) {
        Usuario usuario = us.buscarUsuario(cod_usu);
        modelo.addAttribute("usuario",usuario);


        return "usuario_editar";
    }

    @PostMapping ("/usuario_editar")
    public String editarUsuario(@ModelAttribute ("usuario") Usuario usuario, RedirectAttributes redirectAttrs) {
        try{
            us.editarUsuario(usuario);
            redirectAttrs.addFlashAttribute("exito", "Usuario modificado esxitosamente" );
            } catch (Exception e) {
    redirectAttrs.addFlashAttribute("Error", "Ocurrio un error al editar el Usuario");
        }
        return "redirect:/usuario_listar";


    }



    @GetMapping("/usuario_eliminar")
    public String eliminarUsuario(@org.springframework.web.bind.annotation.RequestParam("cod_usu") int cod_usu, RedirectAttributes flash) {
        try {
            boolean eliminado = us.eliminarUsuario(cod_usu);
            if (eliminado) {
                flash.addFlashAttribute("exito", "El Usuario ha sido eliminado correctamente.");
            }
            else
            {
                flash.addFlashAttribute("error", "El Usuario no existe o ya fue eliminado.");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el Usuario.");
        }
        return "redirect:/usuario_listar";
    }


















    
    
}