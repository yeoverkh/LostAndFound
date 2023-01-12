package lostandfound.services;

import lostandfound.models.lostitem.Place;
import lostandfound.repositories.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public Place findById(Long id) {
        return placeRepository.findById(id).orElse(new Place(id));
    }

    public boolean save(Place place) {
        placeRepository.save(place);

        return true;
    }

    public void deleteById(Long id) {
        placeRepository.deleteById(id);
    }
}
