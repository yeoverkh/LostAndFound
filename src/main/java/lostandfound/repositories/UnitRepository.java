package lostandfound.repositories;

import lostandfound.models.peculiarities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that works with units.
 */
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
