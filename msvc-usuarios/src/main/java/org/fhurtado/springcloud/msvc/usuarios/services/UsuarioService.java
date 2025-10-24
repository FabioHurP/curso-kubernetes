package org.fhurtado.springcloud.msvc.usuarios.services;

import org.fhurtado.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

//Los SERVICE son para trabajar la logica de negocio, lod datos, los DAO

public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);//Evitar objeto null y el null pointer exception
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
}
