package com.seguridad.seguridad.servicio;
import com.seguridad.seguridad.controlador.ControladorInicio;
import com.seguridad.seguridad.modelo.Rol;
import org.springframework.stereotype.Service;
import java.util.List;
import com.seguridad.seguridad.repositorio.RepositorioRol;

@Service 
public class ServicioRol {
    private final RepositorioRol rolRepositorio;

     //Constructor

    public ServicioRol(RepositorioRol rolRepositorio, ControladorInicio controladorInicio){
        this.rolRepositorio = rolRepositorio;
    }

    public List<Rol> listarRoles(){
        return rolRepositorio.rol_listar();
    }

    public void agregarRol(Rol rol) {
        rolRepositorio.rol_agregar(
            rol.getNom_rol(),
            rol.getDes_rol(),
            rol.getEst_rol()
        );
    }

    public Rol buscarRol (int cod_rol) {
        return rolRepositorio.rol_buscar(cod_rol);
    }

    public void editarRol(Rol rol) {
        rolRepositorio.rol_editar(
            rol.getCod_rol(),
            rol.getNom_rol(),
            rol.getDes_rol(),
            rol.getEst_rol()
        );
    }

    public boolean eliminarRol(int cod_rol) {
        Rol rol = rolRepositorio.rol_buscar(cod_rol);
        if (rol != null) {
            rolRepositorio.rol_eliminar(cod_rol);
            return true;
        }
        return false;
    }

}
