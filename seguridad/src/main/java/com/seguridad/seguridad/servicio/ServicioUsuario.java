package com.seguridad.seguridad.servicio;

import com.seguridad.seguridad.modelo.Usuario;

import org.springframework.stereotype.Service;
import java.util.List;

import com.seguridad.seguridad.repositorio.RepositorioUsuario;

@Service 
public class ServicioUsuario {
    private final RepositorioUsuario usuarioRepositorio;

     //Constructor

    public ServicioUsuario(RepositorioUsuario usuarioRepositorio){
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepositorio.usuario_listar();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarioRepositorio.usuario_agregar(
        usuario.getAli_usu(),
        usuario.getEma_usu(),
        usuario.getCla_usu (),
        usuario.getEst_usu ()
        );
    }

    public Usuario buscarUsuario (int cod_usu) {
        return usuarioRepositorio.usuario_buscar(cod_usu);
    }


    public void editarUsuario(Usuario usuario){
        usuarioRepositorio.usuario_editar(
        usuario.getCod_usu(),
        usuario.getAli_usu(),
        usuario.getEma_usu(),
        usuario.getCla_usu (),
        usuario.getEst_usu ()
        );
    }


    public boolean eliminarUsuario( int cod_usu) {
        Usuario usuario = usuarioRepositorio.usuario_buscar(cod_usu);
        if (usuario != null) {
            usuarioRepositorio.usuario_eliminar(cod_usu);
            return true;
        }
        return false; 
            
        
    }


}
