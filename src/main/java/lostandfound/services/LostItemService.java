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

@Service
public class LostItemService {

    private final LostItemRepository lostItemRepository;
    private final PeculiaritiesService peculiaritiesService;
    private final static DateFormat FORMAT = new SimpleDateFormat("HH:mm");

    public LostItemService(LostItemRepository lostItemRepository, PeculiaritiesService peculiaritiesService) {
        this.lostItemRepository = lostItemRepository;
        this.peculiaritiesService = peculiaritiesService;
    }

    public List<LostItem> findAll() {
        return lostItemRepository.findAllByDateBeforeOrderByDateDescTimeDesc(new Date(System.currentTimeMillis()));
    }

    public void save(LostItem item) {
        lostItemRepository.save(item);
    }

    public void save(LostItem lostItem, String stringTime) {
        try {
            lostItem.setTime(new Time(FORMAT.parse(stringTime).getTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        save(lostItem);
    }

    public LostItem findById(Long id) {
        return lostItemRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        lostItemRepository.deleteById(id);
    }

    public void save(Long id, Long peculiarities_id) {
        LostItem lostItem = findById(id);
        Peculiarities peculiarities = peculiaritiesService.findById(peculiarities_id);

        lostItem.getPeculiarities().add(peculiarities);

        lostItemRepository.save(lostItem);
    }

    public void remove(Long id, Long peculiarities_id) {
        LostItem lostItem = findById(id);
        Peculiarities peculiarities = peculiaritiesService.findById(peculiarities_id);

        lostItem.getPeculiarities().remove(peculiarities);

        lostItemRepository.save(lostItem);
    }

    public List<LostItem> sortByDate() {
        return lostItemRepository.findAllByDateBeforeOrderByDateAscTimeAsc(new Date(System.currentTimeMillis()));
    }

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

    public List<LostItem> search(String search) {
        return lostItemRepository.findAllByIdIsNotNullAndNameLikeIgnoreCase("%" + search + "%");
    }

    public LostItem findLastItem() {
        return lostItemRepository.findFirstByDateBeforeOrderByDateDescTimeDesc(new Date(System.currentTimeMillis()));
    }
}
