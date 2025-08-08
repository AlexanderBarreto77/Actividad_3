package publicaciones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "articulos")
@Setter
@Getter
public class Paper extends Publicacion {

    private String areaInvestigacion;
    private String revista;
    private String indexacion;

    @Column(unique = true)
    private String doi;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonIgnore
    private Autor autor;
}
