package lostandfound.repositories;

import lostandfound.models.lostitem.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    List<LostItem> findAllByDateBeforeOrderByDateAscTimeAsc(Date date);

    List<LostItem> findAllByDateBeforeOrderByDateDescTimeDesc(Date date);

    List<LostItem> findAllByDateBeforeOrderByQuantity(Date date);

    List<LostItem> findAllByDateBeforeOrderByQuantityDesc(Date date);

    List<LostItem> findAllByDateBeforeOrderByAssessedValueValue(Date date);

    List<LostItem> findAllByDateBeforeOrderByAssessedValueValueDesc(Date date);

    List<LostItem> findAllByIdIsNotNullAndNameLikeIgnoreCase(String search);

    LostItem findFirstByDateBeforeOrderByDateDescTimeDesc(Date date);
}
