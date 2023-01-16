package lostandfound.services;

import lostandfound.models.lostitem.Currency;
import lostandfound.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that works with currencies.
 */
@Service
public class CurrencyService {

    /**
     * Repository that works with currencies.
     */
    private final CurrencyRepository currencyRepository;

    /**
     * Default constructor that auto wires dependency.
     */
    public CurrencyService(CurrencyRepository currencyRepository) {

        this.currencyRepository = currencyRepository;
    }

    /**
     * Finds all currencies in database.
     * @return list of currencies.
     */
    public List<Currency> findAll() {

        return currencyRepository.findAll();
    }

    /**
     * Saves currency in database.
     * @param currency currency that must be saved.
     */
    public void save(Currency currency) {

        currencyRepository.save(currency);
    }

    /**
     * Finds currency by id.
     * @param id input Long id of required currency.
     * @return currency that have needed id.
     */
    public Currency findById(Long id) {

        return currencyRepository.findById(id).orElse(null);
    }

    /**
     * Deletes currency by id.
     * @param id input Long id of currency that must be deleted.
     */
    public void deleteById(Long id) {

        currencyRepository.deleteById(id);
    }
}
