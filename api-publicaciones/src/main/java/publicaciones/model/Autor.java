package publicaciones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "autores")
@Setter
@Getter
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 30)
    private String apellido;

    @Column(name = "correo", nullable = false, length = 30, unique = true)
    private String email;

    private String nacionalidad;

    private String institucion;

    private String biografia;

    @Column(unique = true, nullable = false, length = 30)
    private String orcid;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    private List<Libro> libros;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    private List<Paper> articulos;
}
