package lostandfound.repositories;

import lostandfound.models.lostitem.AssessedValue;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that works with assessed values.
 */
public interface AssessedValueRepository extends JpaRepository<AssessedValue, Long> {
}
