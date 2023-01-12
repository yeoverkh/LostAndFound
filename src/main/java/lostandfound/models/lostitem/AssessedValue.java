package lostandfound.models.lostitem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AssessedValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @OneToMany(mappedBy = "assessedValue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LostItem> set = new HashSet<>();

    public AssessedValue() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Set<LostItem> getSet() {
        return set;
    }

    public void setSet(Set<LostItem> set) {
        this.set = set;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
