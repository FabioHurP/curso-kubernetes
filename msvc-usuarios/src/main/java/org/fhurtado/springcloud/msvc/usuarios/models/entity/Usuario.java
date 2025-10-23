package org.fhurtado.springcloud.msvc.usuarios.models.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {


    private Long id;
}
