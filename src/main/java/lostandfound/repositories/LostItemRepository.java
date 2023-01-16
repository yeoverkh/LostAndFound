package lostandfound.repositories;

import lostandfound.models.lostitem.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * Repository that works with lost items.
 */
public interface LostItemRepository extends JpaRepository<LostItem, Long> {

    /**
     * Finds all items before {@code Date} date and orders by date and time ascending.
     * @param date input Date before which items must be found.
     * @return list of lost items.
     */
    List<LostItem> findAllByDateBeforeOrderByDateAscTimeAsc(Date date);

    /**
     * Finds all items before {@code Date} date and orders by date and time descending.
     * @param date input Date before which items must be found.
     * @return list of lost items.
     */
    List<LostItem> findAllByDateBeforeOrderByDateDescTimeDesc(Date date);

    /**
     * Finds all items before {@code Date} date and orders by quantity ascending.
     * @param date input Date before which items must be found.
     * @return list of lost items.
     */
    List<LostItem> findAllByDateBeforeOrderByQuantity(Date date);

    /**
     * Finds all items before {@code Date} date and orders by quantity descending.
     * @param date input Date before which items must be found.
     * @return list of lost items.
     */
    List<LostItem> findAllByDateBeforeOrderByQuantityDesc(Date date);

    /**
     * Finds all items before {@code Date} date and orders by assessed value value ascending.
     * @param date input Date before which items must be found.
     * @return list of lost items.
     */
    List<LostItem> findAllByDateBeforeOrderByAssessedValueValue(Date date);

    /**
     * Finds all items before {@code Date} date and orders by assessed value value ascending.
     * @param date input Date before which items must be found.
     * @return list of lost items.
     */
    List<LostItem> findAllByDateBeforeOrderByAssessedValueValueDesc(Date date);

    /**
     * Finds all items names of which contains search String.
     * @param search input String by which searching items.
     * @return list of lost items.
     */
    List<LostItem> findAllByIdIsNotNullAndNameLikeIgnoreCase(String search);
}
