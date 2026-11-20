package com.seguridad.seguridad.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seguridad.seguridad.modelo.TipoPrivacidad;
import com.seguridad.seguridad.servicio.ServicioTipoPrivacidad;





@Controller

public class ControladorTipoPrivacidad {

    private final ServicioTipoPrivacidad tp;

    public ControladorTipoPrivacidad(ServicioTipoPrivacidad tp) {
        this.tp = tp;
    }




        @GetMapping("/tipo_privacidad")
    public String verVistaTipoPrivacidad() {
        return "tipo_privacidad";
    }


    @GetMapping("/tipo_privacidad_listar")
    public String listarTipoPrivacidad(Model modelo) {
        List<TipoPrivacidad> tipos = tp.listarTipoPrivacidad();
        modelo.addAttribute("tipos", tipos);
        return "tipo_privacidad_listar";
    }


@GetMapping("/tipo_privacidad_ver")
    public String verTipoPrivacidad(@org.springframework.web.bind.annotation.RequestParam ("cod_tip") int cod_tip, Model modelo) {
        TipoPrivacidad tipoPrivacidad = tp.verTipoPrivacidad(cod_tip);
        modelo.addAttribute("tipo_privacidad", tipoPrivacidad);
        return "tipo_privacidad_ver";
    }

     @GetMapping("/tipo_privacidad_agregar")
    public String agregarTipoPrivacidad(Model modelo) {
        TipoPrivacidad tipoPrivacidad = new TipoPrivacidad();
        tipoPrivacidad.setNom_tip("");
        tipoPrivacidad.setEst_tip("");
        modelo.addAttribute("tipo_privacidad", tipoPrivacidad);

        return "tipo_privacidad_agregar";
    }

    @PostMapping("/tipo_privacidad_agregar")
    public String agregarTipoPrivacidad(@ModelAttribute ("tipo_privacidad") TipoPrivacidad tipoPrivacidad, RedirectAttributes redirectAttrs) {
        try {
            tipoPrivacidad.setNom_tip(tipoPrivacidad.getNom_tip() != null ? tipoPrivacidad.getNom_tip().trim() : "");
            tipoPrivacidad.setEst_tip(tipoPrivacidad.getEst_tip() != null ? tipoPrivacidad.getEst_tip().toUpperCase() : "A"); 
            tp.agregarTipoPrivacidad(tipoPrivacidad);
            redirectAttrs.addFlashAttribute( "exito", "!Tipo De Privaciad creado correctamente!");
            }
            catch (Exception e) {
                redirectAttrs.addFlashAttribute("error", "Error al crear al crear Tipo de Privacidad: " + e.getMessage());
            }
        return "redirect:/tipo_privacidad_listar";
    }

    @GetMapping("/tipo_privacidad_editar")
    public String editarTipoPrivacidad(@org.springframework.web.bind.annotation.RequestParam ("cod_tip") int cod_tip, Model modelo) {
        TipoPrivacidad tipoPrivacidad = tp.buscarTipoPrivacidad(cod_tip);
        modelo.addAttribute("tipo_privacidad", tipoPrivacidad);
        return "tipo_privacidad_editar";
    }

    @PostMapping("/tipo_privacidad_editar")
    public String editarTipoPrivacidad(@ModelAttribute ("tipo_privacidad") TipoPrivacidad tipoPrivacidad, RedirectAttributes redirectAttrs) {
        try{
            tp.editarTipoPrivacidad(tipoPrivacidad);
            redirectAttrs.addFlashAttribute("exito", "tipo de privacidad modificado exitosamente");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Ocurrio un error al editar el tipo de privacidad");
        }
        return "redirect:/tipo_privacidad_listar";
    }

    @GetMapping("/tipo_privacidad_eliminar")
    public String eliminarTipoPrivacidad(@org.springframework.web.bind.annotation.RequestParam("cod_tip") int cod_tip, RedirectAttributes flash) {
        try {
            boolean eliminado = tp.eliminarTipoPrivacidad(cod_tip);
            if (eliminado) {
                flash.addFlashAttribute("exito", "El tipo de privacidad ha sido eliminado correctamente.");
            }
            else
            {
                flash.addFlashAttribute("error", "El tipo de privacidad no existe o ya fue eliminado.");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el tipo de privacidad.");
        }
        return "redirect:/tipo_privacidad_listar";
    }

}
