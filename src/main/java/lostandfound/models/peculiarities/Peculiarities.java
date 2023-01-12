package lostandfound.models.peculiarities;

import lostandfound.models.lostitem.LostItem;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Peculiarities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Peculiarities name can not be empty")
    private String name;

    @NotNull(message = "Peculiarities must have unit")
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @NotBlank(message = "Peculiarities must have value")
    private String value;

    @ManyToMany
    @JoinTable(
            name = "item_information",
            joinColumns = { @JoinColumn(name = "peculiarities_id") },
            inverseJoinColumns = { @JoinColumn(name = "lost_item_id") }
    )
    private Set<LostItem> lostItems = new HashSet<>();

    public Peculiarities() {
    }

    public Peculiarities(String name, String value, Unit unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<LostItem> getLostItems() {
        return lostItems;
    }

    public void setLostItems(Set<LostItem> lostItems) {
        this.lostItems = lostItems;
    }
}
