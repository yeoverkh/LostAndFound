package lostandfound.repositories;

import lostandfound.models.lostitem.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that works with types.
 */
public interface TypeRepository extends JpaRepository<Type, Long> {
}
