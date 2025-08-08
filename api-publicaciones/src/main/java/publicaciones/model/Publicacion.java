package publicaciones.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "publicaciones")
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private int anioPublicacion;
    private String editorial;
    private String isbn;
    private String resumen;
}
