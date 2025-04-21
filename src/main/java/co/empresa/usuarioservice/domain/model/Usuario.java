package co.empresa.usuarioservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cédula es obligatoria")
    @Column(nullable = false, unique = true)
    private Long cedula;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    @Column(nullable = false)
    private String contrasena;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre completo debe tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String nombreCompleto;

    @NotBlank(message = "El rol es obligatorio")
    @Size(max = 50, message = "El rol no puede exceder los 50 caracteres")
    private String rol;

    @NotNull(message = "El teléfono es obligatorio")
    @Digits(integer = 15, fraction = 0, message = "El teléfono debe ser un número válido de hasta 15 dígitos")
    private Long telefono;
}
