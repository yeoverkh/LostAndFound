package lostandfound.services;

import lostandfound.models.lostitem.Type;
import lostandfound.repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {

        this.typeRepository = typeRepository;
    }

    public List<Type> findAll() {

        return typeRepository.findAll();
    }

    public void save(Type type) {

        typeRepository.save(type);
    }

    public void deleteById(Long id) {

        typeRepository.deleteById(id);
    }

    public Type findById(Long id) {

        return typeRepository.findById(id).orElse(null);
    }
}
