package lostandfound.models.lostitem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    private Double latitude;

    private Double longitude;

    private String shortDescription;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LostItem> lostItems = new HashSet<>();

    public Place() {
    }

    public Place(Long id) {
        this.id = id;
    }

    public Place(Double latitude, Double longitude, String shortDescription) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.shortDescription = shortDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Set<LostItem> getLostItems() {
        return lostItems;
    }

    public void setLostItems(Set<LostItem> lostItems) {
        this.lostItems = lostItems;
    }
}
