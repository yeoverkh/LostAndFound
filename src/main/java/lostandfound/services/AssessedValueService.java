package lostandfound.services;

import lostandfound.models.lostitem.AssessedValue;
import lostandfound.repositories.AssessedValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessedValueService {

    private final AssessedValueRepository assessedValueRepository;

    public AssessedValueService(AssessedValueRepository assessedValueRepository) {

        this.assessedValueRepository = assessedValueRepository;
    }

    public List<AssessedValue> findAll() {

        return assessedValueRepository.findAll();
    }

    public void save(AssessedValue assessedValue) {

        assessedValueRepository.save(assessedValue);
    }

    public AssessedValue findById(Long id) {

        return assessedValueRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {

        assessedValueRepository.deleteById(id);
    }
}
