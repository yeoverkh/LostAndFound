package lostandfound.repositories;

import lostandfound.models.lostitem.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that works with currencies.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
