package lostandfound.services;

import lostandfound.models.lostitem.LostItem;
import lostandfound.models.peculiarities.Peculiarities;
import lostandfound.repositories.LostItemRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Service that works with lost items.
 */
@Service
public class LostItemService {

    /**
     * Repository that works with lost items.
     */
    private final LostItemRepository lostItemRepository;

    /**
     * Repository that works with peculiarities.
     */
    private final PeculiaritiesService peculiaritiesService;

    /**
     * Format of time.
     */
    private final static DateFormat FORMAT = new SimpleDateFormat("HH:mm");

    /**
     * Default constructor that auto wires dependencies.
     */
    public LostItemService(LostItemRepository lostItemRepository, PeculiaritiesService peculiaritiesService) {
        this.lostItemRepository = lostItemRepository;
        this.peculiaritiesService = peculiaritiesService;
    }

    /**
     * Finds all lost items that sorted in natural order (by date).
     * @return list of lost items.
     */
    public List<LostItem> findAll() {
        return lostItemRepository.findAllByDateBeforeOrderByDateDescTimeDesc(new Date(System.currentTimeMillis()));
    }

    /**
     * Saves item in database.
     * @param item item that must be saved.
     */
    public void save(LostItem item) {
        lostItemRepository.save(item);
    }

    /**
     * Saves item with setting time.
     * @param lostItem item that must be saved.
     * @param stringTime time of found this item.
     */
    public void save(LostItem lostItem, String stringTime) {
        try {
            lostItem.setTime(new Time(FORMAT.parse(stringTime).getTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        save(lostItem);
    }

    /**
     * Finds item by id.
     * @param id input Long id by which item must be found.
     * @return item with needed id.
     */
    public LostItem findById(Long id) {
        return lostItemRepository.findById(id).orElse(null);
    }

    /**
     * Deletes item by id.
     * @param id input Long id by which item must be deleted.
     */
    public void deleteById(Long id) {
        lostItemRepository.deleteById(id);
    }

    /**
     * Adds some peculiarities to item. Connects item with peculiarities.
     * @param id input Long id of item.
     * @param peculiarities_id input Long id of peculiarities.
     */
    public void save(Long id, Long peculiarities_id) {
        LostItem lostItem = findById(id);

        Peculiarities peculiarities = peculiaritiesService.findById(peculiarities_id);

        lostItem.getPeculiarities().add(peculiarities);

        lostItemRepository.save(lostItem);
    }

    /**
     * Removes some peculiarities from item. Removes connection between item and peculiarities.
     * @param id input Long id of item.
     * @param peculiarities_id input Long id of peculiarities.
     */
    public void remove(Long id, Long peculiarities_id) {
        LostItem lostItem = findById(id);

        Peculiarities peculiarities = peculiaritiesService.findById(peculiarities_id);

        lostItem.getPeculiarities().remove(peculiarities);

        lostItemRepository.save(lostItem);
    }

    /**
     * Sorts lost items by date and time ascending.
     * @return sorted list of lost items.
     */
    public List<LostItem> sortByDate() {
        return lostItemRepository.findAllByDateBeforeOrderByDateAscTimeAsc(new Date(System.currentTimeMillis()));
    }

    /**
     * Sorts lost items by some characteristics.
     * @param value characteristic by which items will be sorted.
     * @param sortType type of sort: ascending or descending.
     * @return list of lost items.
     */
    public List<LostItem> sort(String value, String sortType) {
        if (value.equals("date")) {
            if (sortType.equals("ascending")) {
                return sortByDate();
            } else {
                return findAll();
            }
        } else if (value.equals("quantity")) {
            if (sortType.equals("ascending")) {
                return lostItemRepository.findAllByDateBeforeOrderByQuantity(new Date(System.currentTimeMillis()));
            } else {
                return lostItemRepository.findAllByDateBeforeOrderByQuantityDesc(new Date(System.currentTimeMillis()));
            }
        } else {
            if (sortType.equals("ascending")) {
                return lostItemRepository.findAllByDateBeforeOrderByAssessedValueValue(new Date(System.currentTimeMillis()));
            } else {
                return lostItemRepository.findAllByDateBeforeOrderByAssessedValueValueDesc(new Date(System.currentTimeMillis()));
            }
        }
    }

    /**
     * Finds all items that contains in theirs names {@code search} string.
     * @param search input String by which items are searches.
     * @return list of items.
     */
    public List<LostItem> search(String search) {
        return lostItemRepository.findAllByIdIsNotNullAndNameLikeIgnoreCase("%" + search + "%");
    }
}
