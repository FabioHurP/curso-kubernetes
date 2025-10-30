package org.fhurtado.springcloud.msvc.usuarios.repositories;

import org.fhurtado.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> { //First parameter name Clase, segundo = long tipo dato pk
    //Los metodos deben llevar la palabra clave find para q Nos ayude con las query
    Optional<Usuario> findByEmail(String email);

    //El email del parametro de adapta y se reemplaza en el ?1 del @Query
    @Query("select u from Usuario u where u.email=?1")
    Optional<Usuario> buscarPorEmail(String email);

    boolean existsByEmail(String email);

}
