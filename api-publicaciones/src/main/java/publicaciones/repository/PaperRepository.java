package publicaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import publicaciones.model.Paper;

public interface PaperRepository extends JpaRepository<Paper, Long> {
}
