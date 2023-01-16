package lostandfound.models.lostitem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents currency for assessed value.
 * A lot of assessed values may have one currency.
 * Every assessed value must have only one currency.
 */
@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<AssessedValue> assessedValues = new HashSet<>();

    public Currency() {
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

    public Set<AssessedValue> getAssessedValues() {
        return assessedValues;
    }

    public void setAssessedValues(Set<AssessedValue> assessedValues) {
        this.assessedValues = assessedValues;
    }
}
