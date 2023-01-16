package lostandfound.models.lostitem;

import lostandfound.models.peculiarities.Peculiarities;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents lost item.
 */
@Entity
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Lost item name can not be empty")
    @Length(max = 255, message = "Name too long (more than 255 chars)")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

    @NotNull(message = "Item must have quantity")
    private Integer quantity;

    private Date date;

    private Time time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessed_value_id")
    private AssessedValue assessedValue;

    private String filename;

    @ManyToMany
    @JoinTable(
            name = "item_information",
            joinColumns = { @JoinColumn(name = "lost_item_id") },
            inverseJoinColumns = { @JoinColumn(name = "peculiarities_id") }
    )
    private Set<Peculiarities> peculiarities = new HashSet<>();


    public LostItem() {
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setTime(String time) {
        this.time = Time.valueOf(time);
    }

    public AssessedValue getAssessedValue() {
        return assessedValue;
    }

    public void setAssessedValue(AssessedValue assessedValue) {
        this.assessedValue = assessedValue;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<Peculiarities> getPeculiarities() {
        return peculiarities;
    }

    public void setPeculiarities(Set<Peculiarities> peculiarities) {
        this.peculiarities = peculiarities;
    }
}
