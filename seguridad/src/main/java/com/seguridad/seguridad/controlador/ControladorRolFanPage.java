package com.seguridad.seguridad.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.seguridad.seguridad.modelo.RolFanPage;
import com.seguridad.seguridad.servicio.ServicioFanPage;
import com.seguridad.seguridad.servicio.ServicioPersona;
import com.seguridad.seguridad.servicio.ServicioRol;
import com.seguridad.seguridad.servicio.ServicioRolFanPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller 
public class ControladorRolFanPage {


    private final ServicioRolFanPage rfp;
    private final ServicioPersona persona;
    private final ServicioFanPage fanPage;
    private final ServicioRol rol;

    

    public ControladorRolFanPage(ServicioRolFanPage rfp, ServicioPersona persona, ServicioFanPage fanPage, ServicioRol rol) {
        this.rfp = rfp;
        this.persona = persona;
        this.fanPage = fanPage;
        this.rol = rol;
    }

    @GetMapping("/rol_fan_page")
    public String verVistaRolFanPage() {
        return "rol_fan_page";
    }
    
    @GetMapping("/rol_fan_page_listar")
    public String listarRolFanPage(Model modelo) {
        List<RolFanPage> rolfanpages = rfp.listarRolFanPage();
        modelo.addAttribute("rolfanpages", rolfanpages);
        return "rol_fan_page_listar";
    }

    @GetMapping("/rol_fan_page_agregar")
    public String verAgregarRolFanPage(Model modelo) {
        modelo.addAttribute("rolfanpage", new RolFanPage());
        modelo.addAttribute("personas", persona.listarPersonas());
        modelo.addAttribute("fanpages", fanPage.listarFanpages());
        modelo.addAttribute("roles", rol.listarRoles());
        return "rol_fan_page_agregar";
    }
    
    @PostMapping("/rol_fan_page_agregar")
    public String agregarRolFanPage(@ModelAttribute ("rolfanpage") RolFanPage rolfanpage, RedirectAttributes redirctAttrs) {
        try {
            rolfanpage.setEst_rol_fan(rolfanpage.getEst_rol_fan() !=null ? rolfanpage.getEst_rol_fan().trim().toUpperCase() : "A");
            rfp.agregarRolFanPage(rolfanpage);
            redirctAttrs.addFlashAttribute("exito", "Rol Fan Page creado correctamente");
        } catch (Exception e) {
            redirctAttrs.addFlashAttribute("error", "Error al crear el rol fan page: "+e.getMessage());
        }
        return "redirect:/rol_fan_page_listar";
    }

    @GetMapping("/rol_fan_page_ver")
    public String verDetalleRolFanPage(@RequestParam("cod_rol_fan") int cod_rol_fan, Model modelo) {
        RolFanPage rolfanpage = rfp.buscarRolFanPage(cod_rol_fan);
        modelo.addAttribute("rolfanpage", rolfanpage);
        return "rol_fan_page_ver";
    }

    @GetMapping("/rol_fan_page_editar")
    public String verEditarRolFanPage(@RequestParam("cod_rol_fan") int cod_rol_fan, Model modelo) {
        RolFanPage rolfanpage = rfp.buscarRolFanPage(cod_rol_fan);
        modelo.addAttribute("rolfanpage", rolfanpage);
        
        // Cargar las listas para los <select>
        modelo.addAttribute("personas", persona.listarPersonas());
        modelo.addAttribute("fanpages", fanPage.listarFanpages());
        modelo.addAttribute("roles", rol.listarRoles());
        
        return "rol_fan_page_editar";
    }

    @PostMapping("/rol_fan_page_editar")
    public String editarRolFanPage(@ModelAttribute("rolfanpage") RolFanPage rolfanpage, RedirectAttributes redirectAttrs) {
        try {
            rfp.modificarRolFanPage(rolfanpage);
            redirectAttrs.addFlashAttribute("exito", "Rol Fan Page modificado exitosamente");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Ocurrio un error al editar el rol fan page"+e.getMessage());
        }
        
        return "redirect:/rol_fan_page_listar";
    }
    
    @GetMapping("/rol_fan_page_eliminar")
    public String eliminarRolFanPage(@RequestParam("cod_rol_fan") int cod_rol_fan, RedirectAttributes flash) {
        try {
            boolean eliminado = rfp.eliminarRolFanPage(cod_rol_fan);
            if (eliminado) {
                flash.addFlashAttribute("exito", "El Rol Fan Page ha sido eliminado correctamente.");
            } else {
                flash.addFlashAttribute("error", "El Rol Fan Page no existe o ya fue eliminado.");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el Rol Fan Page.");
        }
        return "redirect:/rol_fan_page_listar";
    }

}
