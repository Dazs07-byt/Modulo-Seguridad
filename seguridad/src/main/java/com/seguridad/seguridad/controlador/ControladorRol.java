package com.seguridad.seguridad.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import com.seguridad.seguridad.modelo.Rol;
import com.seguridad.seguridad.servicio.ServicioRol;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;







@Controller
public class ControladorRol {

    private final ServicioRol rs;

    public ControladorRol(ServicioRol rs) {
        this.rs = rs;
    }

    @GetMapping("/rol")
    public String verVistaRol() {
        return "rol";
    }

    @GetMapping("/rol_agregar")
    public String agregarRol(Model modelo) {
        Rol rol = new Rol();
        rol.setNom_rol("");
        rol.setDes_rol("");
        rol.setEst_rol("");
        modelo.addAttribute("rol",rol);
        return "rol_agregar";
    }

    @PostMapping("/rol_agregar")
    public String agregarRol(@ModelAttribute("rol") Rol rol, RedirectAttributes redirectAttrs) {
    try {
        rol.setNom_rol(rol.getNom_rol() != null ? rol.getNom_rol().trim() : "");
        rol.setDes_rol(rol.getDes_rol() != null ? rol.getDes_rol().trim() : "");
        rol.setEst_rol(rol.getEst_rol() != null ? rol.getEst_rol().toUpperCase() : "A");
        rs.agregarRol(rol);
        redirectAttrs.addFlashAttribute("exito", "Rol creado correctamente");
    } catch (Exception e) {
        redirectAttrs.addFlashAttribute("error", "Error al crear el rol: " +e.getMessage());
    }
    return "redirect:/rol_listar";
}
    


    @GetMapping("/rol_ver")
    public String buscarRol(@org.springframework.web.bind.annotation.RequestParam ("cod_rol") int cod_rol, Model modelo) {
        Rol rol = rs.buscarRol(cod_rol);
        modelo.addAttribute("rol", rol);
        return "rol_ver";
    }

    @GetMapping("/rol_listar")
    public String listarRoles(Model modelo) {
        List<Rol> roles = rs.listarRoles();
        modelo.addAttribute("roles", roles);
        return "rol_listar";
    }

    @GetMapping("/rol_editar")
    public String editarRol(@org.springframework.web.bind.annotation.RequestParam ("cod_rol") int cod_rol, Model modelo) {
        Rol rol = rs.buscarRol(cod_rol);
        modelo.addAttribute("rol", rol);
        return "rol_editar";
    }

    @PostMapping("/rol_editar")
    public String editarRol(@ModelAttribute ("rol") Rol rol, RedirectAttributes redirectAttrs) {
        try{
            rs.editarRol(rol);
            redirectAttrs.addFlashAttribute("exito", "rol modificado exitosamente");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Ocurrio un error al editar el rol");
        }
        return "redirect:/rol_listar";
    }
    
    @GetMapping("/rol_eliminar")
    public String eliminarRol(@org.springframework.web.bind.annotation.RequestParam("cod_rol") int cod_rol, RedirectAttributes flash) {
        try {
            boolean eliminado = rs.eliminarRol(cod_rol);
            if (eliminado) {
                flash.addFlashAttribute("exito", "El rol ha sido eliminado correctamente.");
            }
            else
            {
                flash.addFlashAttribute("error", "El rol no existe o ya fue eliminado.");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el rol.");
        }
        return "redirect:/rol_listar";
    }
    
    
}