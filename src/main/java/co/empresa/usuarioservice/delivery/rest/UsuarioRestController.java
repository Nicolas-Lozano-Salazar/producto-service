package co.empresa.usuarioservice.delivery.rest;

import co.empresa.usuarioservice.domain.exception.NoHayUsuariosException;
import co.empresa.usuarioservice.domain.exception.PaginaSinUsuariosException;
import co.empresa.usuarioservice.domain.exception.UsuarioNoEncontradoException;
import co.empresa.usuarioservice.domain.exception.ValidationException;
import co.empresa.usuarioservice.domain.model.Usuario;
import co.empresa.usuarioservice.domain.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuario-service")
public class UsuarioRestController {

    private final IUsuarioService usuarioService;

    private static final String MENSAJE = "mensaje";
    private static final String USUARIO = "usuario";
    private static final String USUARIOS = "usuarios";

    public UsuarioRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Listar todos los usuarios.
     */
    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            throw new NoHayUsuariosException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(USUARIOS, usuarios);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar usuarios con paginación.
     */
    @GetMapping("/usuarios/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Usuario> usuarios = usuarioService.findAll(pageable);
        if (usuarios.isEmpty()) {
            throw new PaginaSinUsuariosException(page);
        }
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Crear un nuevo usuario con validaciones.
     */
    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Usuario nuevoUsuario = usuarioService.save(usuario);
        response.put(MENSAJE, "El usuario ha sido creado con éxito!");
        response.put(USUARIO, nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Eliminar un usuario por su objeto.
     */
    @DeleteMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Usuario usuario) {
        usuarioService.findById(usuario.getId())
                .orElseThrow(() -> new UsuarioNoEncontradoException(usuario.getId()));
        usuarioService.delete(usuario);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El usuario ha sido eliminado con éxito!");
        response.put(USUARIO, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar un usuario.
     */
    @PutMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        usuarioService.findById(usuario.getId())
                .orElseThrow(() -> new UsuarioNoEncontradoException(usuario.getId()));
        Map<String, Object> response = new HashMap<>();
        Usuario usuarioActualizado = usuarioService.update(usuario);
        response.put(MENSAJE, "El usuario ha sido actualizado con éxito!");
        response.put(USUARIO, usuarioActualizado);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener un usuario por su ID.
     */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El usuario ha sido encontrado con éxito!");
        response.put(USUARIO, usuario);
        return ResponseEntity.ok(response);
    }
}
