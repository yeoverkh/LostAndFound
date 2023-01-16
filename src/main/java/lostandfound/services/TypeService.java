package lostandfound.services;

import lostandfound.models.lostitem.Type;
import lostandfound.repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that works with types.
 */
@Service
public class TypeService {

    /**
     * Repository that works with types.
     */
    private final TypeRepository typeRepository;

    /**
     * Default constructor that auto wires dependency.
     */
    public TypeService(TypeRepository typeRepository) {

        this.typeRepository = typeRepository;
    }

    /**
     * Finds all types.
     * @return list of types.
     */
    public List<Type> findAll() {

        return typeRepository.findAll();
    }

    /**
     * Saves type.
     * @param type type that must be saved.
     */
    public void save(Type type) {

        typeRepository.save(type);
    }

    /**
     * Deletes type by id.
     * @param id input Long id by which type must be deleted.
     */
    public void deleteById(Long id) {

        typeRepository.deleteById(id);
    }

    /**
     * Finds type by id.
     * @param id input Long id by which type must be found.
     * @return type with required id.
     */
    public Type findById(Long id) {

        return typeRepository.findById(id).orElse(null);
    }
}
