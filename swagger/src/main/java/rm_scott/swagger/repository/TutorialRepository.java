package rm_scott.swagger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import rm_scott.swagger.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished ( boolean published );

    List<Tutorial> findByTitleContaining ( String title );
}