package publicaciones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "libros")
@Setter
@Getter
public class Libro extends Publicacion {
    
    private String genero;
    private int numeroPaginas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonIgnore
    private Autor autor;
}
