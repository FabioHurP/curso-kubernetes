package org.fhurtado.springcloud.msvc.cursos.repositories;

import org.fhurtado.springcloud.msvc.cursos.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {//Se pone Long porque es el tipo de la llave de la entity Curso
}
