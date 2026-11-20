package com.seguridad.seguridad.servicio;


import com.seguridad.seguridad.modelo.TipoPrivacidad;

import java.util.List;
import com.seguridad.seguridad.servicio.ServicioTipoPrivacidad;
import org.springframework.stereotype.Service;

import com.seguridad.seguridad.repositorio.RepositorioTipoPrivacidad;

@Service 
public class ServicioTipoPrivacidad {
    private final RepositorioTipoPrivacidad tipoPrivacidadRepositorio;

     //Constructor

    public ServicioTipoPrivacidad(RepositorioTipoPrivacidad tipoPrivacidadRepositorio){
        this.tipoPrivacidadRepositorio = tipoPrivacidadRepositorio;
    }

    public List<TipoPrivacidad> listarTipoPrivacidad(){
        return tipoPrivacidadRepositorio.tipo_privacidad_listar();
    }

    public void agregarTipoPrivacidad(TipoPrivacidad tipoPrivacidad) {
        tipoPrivacidadRepositorio.tipo_privacidad_agregar(
            tipoPrivacidad.getNom_tip(),
            tipoPrivacidad.getEst_tip()
        );
    }
    

    public TipoPrivacidad buscarTipoPrivacidad (int cod_tip) {
        return tipoPrivacidadRepositorio.tipo_privacidad_buscar(cod_tip);
    }

    public void editarTipoPrivacidad(TipoPrivacidad tipoPrivacidad) {
        tipoPrivacidadRepositorio.tipo_privacidad_editar(
            tipoPrivacidad.getCod_tip(),
            tipoPrivacidad.getNom_tip(),
            tipoPrivacidad.getEst_tip()
        );
    



}

    public TipoPrivacidad verTipoPrivacidad (int cod_tip) {
        return tipoPrivacidadRepositorio.tipo_privacidad_buscar(cod_tip);
    }

    public boolean eliminarTipoPrivacidad(int cod_tip) {
        TipoPrivacidad tipoPrivacidad = tipoPrivacidadRepositorio.tipo_privacidad_buscar(cod_tip);
        if (tipoPrivacidad != null) {
            tipoPrivacidadRepositorio.tipo_privacidad_eliminar(cod_tip);
            return true;
        }
        return false;
    }    

}
