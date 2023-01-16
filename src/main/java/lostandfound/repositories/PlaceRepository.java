package lostandfound.repositories;

import lostandfound.models.lostitem.Place;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that works with places.
 */
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
