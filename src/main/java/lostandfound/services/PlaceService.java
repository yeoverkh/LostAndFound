package lostandfound.services;

import lostandfound.models.lostitem.Place;
import lostandfound.repositories.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that works with places.
 */
@Service
public class PlaceService {

    /**
     * Repository that works with places.
     */
    private final PlaceRepository placeRepository;

    /**
     * Default constructor that auto wires dependency.
     */
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    /**
     * Finds all places in database.
     * @return list of places.
     */
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    /**
     * Finds place by id.
     * @param id input Long id by which place must be found.
     * @return place with required id.
     */
    public Place findById(Long id) {
        return placeRepository.findById(id).orElse(new Place(id));
    }

    /**
     * Saves place to database.
     * @param place place that must be saved in database.
     */
    public void save(Place place) {
        placeRepository.save(place);
    }

    /**
     * Deletes place by id.
     * @param id input Long id by which place must be deleted.
     */
    public void deleteById(Long id) {
        placeRepository.deleteById(id);
    }
}
