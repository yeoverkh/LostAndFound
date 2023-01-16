package lostandfound.models.lostitem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents type of lost item.
 * A lot of items may have one type.
 * Every item must have only one type.
 */
@Entity
@Table(name = "typ")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LostItem> lostItems = new HashSet<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LostItem> getLostItems() {
        return lostItems;
    }

    public void setLostItems(Set<LostItem> lostItems) {
        this.lostItems = lostItems;
    }
}
