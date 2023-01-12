package lostandfound.services;

import lostandfound.models.peculiarities.Unit;
import lostandfound.repositories.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit findById(Long id) {
        return unitRepository.findById(id).orElse(null);
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    public void save(Unit unit) {
        unitRepository.save(unit);
    }

    public void deleteById(Long id) {
        unitRepository.deleteById(id);
    }
}
