package org.fhurtado.springcloud.msvc.usuarios.controllers;

import jakarta.validation.Valid;
import org.fhurtado.springcloud.msvc.usuarios.models.entity.Usuario;
import org.fhurtado.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//Los controlers manejan la peticion del usuario el request y va interactual con el service con la logica de negocio,
// para devolver la informacion los datos como un JSON, por lo que vamos a trabajar con Api Rest
//Gracias al RestController y el response Body todo se devuelve en JSON
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService service;//Importamos la interface y Permite obtener todos los metodos de la class UsuarioServiceImpl, No apuntar directamente a la class UsuarioServiceImpl

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {//El param del GetMapping debe ser igual al q se recibe como parametro de PathVariable
        Optional<Usuario> usuarioOptional = service.porId(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    //@Valid Para q tome las validaciones del Entity
    //BindingResult result para manejas respuestas
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return validarErrores(result);
        }
        //if (!usuario.getEmail().isEmpty() && service.buscarPorEmail(usuario.getEmail()).isPresent()) {
        if (!usuario.getEmail().isEmpty() && service.existePorEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "ya existe un usuario con el mismo correo"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validarErrores(result);
        }
        Optional<Usuario> o = service.porId(id);
        if (o.isPresent()) {
            Usuario usuarioDb = o.get();
            if (!usuario.getEmail().isEmpty() && !usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail()) && service.buscarPorEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("mensaje", "ya existe un usuario con el mismo correo"));
            }
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));// service.guardar(usuarioDb) Devuelve info y lo ponemos en el BODY
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validarErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            //error.getField() da el error con el nombre del campos
            errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
