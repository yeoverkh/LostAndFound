package lostandfound.models.peculiarities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Class that represents unit for peculiarities.
 * A lot of peculiarities may have one unit.
 * Some peculiarities must have only one unit.
 */
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Unit can not be empty")
    private String name;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Peculiarities> peculiarities;

    public Unit() {
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

    public Set<Peculiarities> getPeculiarities() {
        return peculiarities;
    }

    public void setPeculiarities(Set<Peculiarities> peculiarities) {
        this.peculiarities = peculiarities;
    }
}
