package lostandfound.services;

import lostandfound.models.peculiarities.Unit;
import lostandfound.repositories.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that works with units.
 */
@Service
public class UnitService {

    /**
     * Repository that works with units.
     */
    private final UnitRepository unitRepository;

    /**
     * Default constructor that auto wires dependency.
     */
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    /**
     * Finds unit by id.
     * @param id input Long id by which unit must be found.
     * @return unit with required id.
     */
    public Unit findById(Long id) {
        return unitRepository.findById(id).orElse(null);
    }

    /**
     * Finds all units in database.
     * @return list of units.
     */
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    /**
     * Saves unit to database.
     * @param unit unit that must be saved in database.
     */
    public void save(Unit unit) {
        unitRepository.save(unit);
    }

    /**
     * Deletes unit by id.
     * @param id input Long id of unit that must be deleted.
     */
    public void deleteById(Long id) {
        unitRepository.deleteById(id);
    }
}
