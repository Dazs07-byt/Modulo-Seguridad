package com.seguridad.seguridad.servicio;

import java.util.List;


import org.springframework.stereotype.Service;
import com.seguridad.seguridad.repositorio.RepositorioFanPage;
import com.seguridad.seguridad.modelo.FanPage;

@Service
public class ServicioFanPage {
    
    private final RepositorioFanPage FanPageRepositorio;

    public ServicioFanPage(RepositorioFanPage FanPageRepositorio) {
        this.FanPageRepositorio = FanPageRepositorio;
    }

    public List<FanPage> listarFanpages() {
        return FanPageRepositorio.fan_page_listar();
    }


}
