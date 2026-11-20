package com.seguridad.seguridad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.seguridad.seguridad.modelo.Rol;

public interface RepositorioRol extends JpaRepository<Rol, Integer> {
    //Metodo para listar con nuestro procedimiento almacenado
    // es la forma mas segura, en vez de implementar findAll por defecto
    @Query(value = "SELECT * FROM seguridad.rol_listar()", nativeQuery = true)
    List <Rol> rol_listar();

    //Metodo para annadir un nuevo rol con nuestro procedimiento almacenado de la forma mas segura
    @Transactional 
    @Query (value = "SELECT seguridad.rol_agregar(:nom_rol, :des_rol, :est_rol)", nativeQuery = true)
    Integer rol_agregar(@Param("nom_rol") String nom_rol, @Param("des_rol") String des_rol, @Param("est_rol") String est_rol);

    @Query (value = "SELECT * FROM seguridad.rol_buscar(:cod_rol)", nativeQuery = true)
    Rol rol_buscar(@Param("cod_rol") int cod_rol);

    @Query (value = "SELECT seguridad.rol_modificar(:cod_rol, :nom_rol, :des_rol, :est_rol)", nativeQuery = true) 
    Integer rol_editar(@Param("cod_rol") int cod_rol, @Param ("nom_rol") String nom_rol, @Param("des_rol") String des_rol, @Param("est_rol") String est_rol);

    @Transactional 
    @Query (value = "SELECT seguridad.rol_eliminar(:cod_rol)", nativeQuery = true)
    Integer rol_eliminar(@Param("cod_rol") int cod_rol);

}
