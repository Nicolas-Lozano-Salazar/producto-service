package co.empresa.usuarioservice.domain.exception;

public class PaginaSinUsuariosException extends RuntimeException {
    public PaginaSinUsuariosException(int page) {
        super("No hay usuarios en la página solicitada: " + page);
    }
}