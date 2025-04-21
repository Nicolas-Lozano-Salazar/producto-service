package co.empresa.usuarioservice.domain.repository;

import co.empresa.usuarioservice.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que hereda de JpaRepository para realizar las
 * operaciones de CRUD paginacion y ordenamiento sobre la entidad Usuario
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
