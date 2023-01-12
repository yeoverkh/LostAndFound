package lostandfound.services;

import lostandfound.models.peculiarities.Peculiarities;
import lostandfound.repositories.PeculiaritiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeculiaritiesService {

    private final PeculiaritiesRepository peculiaritiesRepository;

    public PeculiaritiesService(PeculiaritiesRepository peculiaritiesRepository) {
        this.peculiaritiesRepository = peculiaritiesRepository;
    }

    public List<Peculiarities> findAll() {
        return peculiaritiesRepository.findAll();
    }

    public void save(Peculiarities peculiarities) {
        peculiaritiesRepository.save(peculiarities);
    }

    public Peculiarities findById(Long id) {
        return peculiaritiesRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        peculiaritiesRepository.deleteById(id);
    }
}
