package lostandfound.services;

import lostandfound.models.lostitem.Currency;
import lostandfound.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {

        this.currencyRepository = currencyRepository;
    }

    public List<Currency> findAll() {

        return currencyRepository.findAll();
    }

    public void save(Currency currency) {

        currencyRepository.save(currency);
    }

    public Currency findById(Long id) {

        return currencyRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {

        currencyRepository.deleteById(id);
    }
}
