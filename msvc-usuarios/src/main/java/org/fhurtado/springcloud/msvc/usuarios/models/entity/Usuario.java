package org.fhurtado.springcloud.msvc.usuarios.models.entity;


//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id//Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column() ponemos el nombre por si en la tabla de BD se llama diferente
    //@NotEmpty valida que el campo no sea vacio, otra opcion @NotEmpty(message = "mensaje personalizado")
    //@NotBlank valida que el campo no sea vacio y no tenga espacios
    @NotBlank
    private String nombre;

    @NotBlank
    @Email//Para q tenga el formato correcto
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//Getter and Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
