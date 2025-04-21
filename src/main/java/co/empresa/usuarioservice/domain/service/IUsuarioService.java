package co.empresa.usuarioservice.domain.service;

import co.empresa.usuarioservice.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interface que define los métodos del servicio de negocio para la entidad Usuario
 */
public interface IUsuarioService {

    /**
     * Guarda un nuevo usuario
     * @param usuario Usuario a guardar
     * @return Usuario guardado
     */
    Usuario save(Usuario usuario);

    /**
     * Elimina un usuario existente
     * @param usuario Usuario a eliminar
     */
    void delete(Usuario usuario);

    /**
     * Busca un usuario por su ID
     * @param id ID del usuario
     * @return Optional con el usuario si existe
     */
    Optional<Usuario> findById(Long id);

    /**
     * Actualiza los datos de un usuario
     * @param usuario Usuario con los nuevos datos
     * @return Usuario actualizado
     */
    Usuario update(Usuario usuario);

    /**
     * Lista todos los usuarios sin paginación
     * @return Lista de usuarios
     */
    List<Usuario> findAll();

    /**
     * Lista los usuarios con paginación
     * @param pageable Configuración de paginación
     * @return Página de usuarios
     */
    Page<Usuario> findAll(Pageable pageable);
}
