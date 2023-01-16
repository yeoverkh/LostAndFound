package lostandfound.services;

import lostandfound.models.lostitem.AssessedValue;
import lostandfound.repositories.AssessedValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that works with assessed values.
 */
@Service
public class AssessedValueService {

    /**
     * Repository that works with assessed values.
     */
    private final AssessedValueRepository assessedValueRepository;

    /**
     * Default constructor that auto wires dependency.
     */
    public AssessedValueService(AssessedValueRepository assessedValueRepository) {

        this.assessedValueRepository = assessedValueRepository;
    }

    /**
     * Finds all assessed values.
     * @return list of all assessed values in database.
     */
    public List<AssessedValue> findAll() {

        return assessedValueRepository.findAll();
    }

    /**
     * Saves assessed value in database.
     * @param assessedValue assessed value that must be saved.
     */
    public void save(AssessedValue assessedValue) {

        assessedValueRepository.save(assessedValue);
    }

    /**
     * Finds one assessed value by id.
     * @param id input Long id of assessed value that must be found.
     * @return assessed value.
     */
    public AssessedValue findById(Long id) {

        return assessedValueRepository.findById(id).orElse(null);
    }

    /**
     * Deletes assessed value by id.
     * @param id id of assessed value that must be deleted.
     */
    public void deleteById(Long id) {

        assessedValueRepository.deleteById(id);
    }
}
