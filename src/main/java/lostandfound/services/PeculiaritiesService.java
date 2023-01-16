package lostandfound.services;

import lostandfound.models.peculiarities.Peculiarities;
import lostandfound.repositories.PeculiaritiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that works with peculiarities.
 */
@Service
public class PeculiaritiesService {

    /**
     * Repository that works with peculiarities.
     */
    private final PeculiaritiesRepository peculiaritiesRepository;

    /**
     * Default constructor that auto wires dependency.
     */
    public PeculiaritiesService(PeculiaritiesRepository peculiaritiesRepository) {
        this.peculiaritiesRepository = peculiaritiesRepository;
    }

    /**
     * Finds all peculiarities in database.
     * @return list of all peculiarities.
     */
    public List<Peculiarities> findAll() {
        return peculiaritiesRepository.findAll();
    }

    /**
     * Saves peculiarities to database.
     * @param peculiarities peculiarities that must be saved.
     */
    public void save(Peculiarities peculiarities) {
        peculiaritiesRepository.save(peculiarities);
    }

    /**
     * Finds peculiarities by id.
     * @param id input Long id by which peculiarities must be found.
     * @return peculiarities with needed id.
     */
    public Peculiarities findById(Long id) {
        return peculiaritiesRepository.findById(id).orElse(null);
    }

    /**
     * Deletes peculiarities by id.
     * @param id input Long id by which peculiarities must be deleted.
     */
    public void deleteById(Long id) {
        peculiaritiesRepository.deleteById(id);
    }
}
