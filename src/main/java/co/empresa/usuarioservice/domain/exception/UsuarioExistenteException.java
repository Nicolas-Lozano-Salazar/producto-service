package co.empresa.usuarioservice.domain.exception;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(String nombre) {
        super("El usuario con nombre '" + nombre + "' ya existe.");
    }
}
