package rm_scott.bezkoder.pbezkoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import rm_scott.bezkoder.pbezkoder.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished ( boolean published );

    List<Tutorial> findByTitleContaining ( String title );
}
