package lostandfound.repositories;

import lostandfound.models.peculiarities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
