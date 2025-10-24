package org.fhurtado.springcloud.msvc.usuarios.models.entity;


//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id//Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column()ponemos el nombre por si en la tabla de BD se llama diferente
    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
