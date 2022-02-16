package sr.unasat.travelapp.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "transport_company")
public class TransportCompany {

    @Id
    @Column(name = "transport_company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transportCompanyId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToMany
    @JoinTable(name = "transport_company_destination",
            joinColumns = {@JoinColumn(name = "transport_company_id")},
            inverseJoinColumns = {@JoinColumn(name = "destination_id")})
    private Set<Destination> destinations;

    public Long getTransportCompanyId() {
        return transportCompanyId;
    }

    public void setTransportCompanyId(Long transportCompany) {
        this.transportCompanyId = transportCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "TransportCompany{" +
                "transportCompany=" + transportCompanyId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rating=" + rating +
                "}\n";
    }
}
