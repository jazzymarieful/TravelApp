package sr.unasat.travelapp.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @Column(name = "destination_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "destination")
    private Set<Accommodation> accommodations;

    @ManyToMany
    @JoinTable(name = "transport_company_destination",
            joinColumns = {@JoinColumn(name = "destination_id")},
            inverseJoinColumns = {@JoinColumn(name = "transport_company_id")})
    private Set<TransportCompany> transportCompanies;


    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<TransportCompany> getTransportCompanies() {
        return transportCompanies;
    }

    public void setTransportCompanies(Set<TransportCompany> transportCompanies) {
        this.transportCompanies = transportCompanies;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "destinationId=" + destinationId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                "}\n";
    }
}
