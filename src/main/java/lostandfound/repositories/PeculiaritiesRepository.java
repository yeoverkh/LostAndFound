package lostandfound.repositories;

import lostandfound.models.peculiarities.Peculiarities;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that works with peculiarities.
 */
public interface PeculiaritiesRepository extends JpaRepository<Peculiarities, Long> {
}
