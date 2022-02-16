package sr.unasat.travelapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "travel_segment")
public class TravelSegment {

    @Id
    @Column(name = "travel_segment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelSegmentId;

    @OneToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @OneToOne
    @JoinColumn(name = "accomodation_id", nullable = false)
    private Accommodation accommodation;

    @OneToOne
    @JoinColumn(name = "follow_up_destination_id")
    private Destination followUpDestination;

    @OneToOne
    @JoinColumn(name = "follow_up_accomodation_id")
    private Accommodation followUpAccommodation;

    @OneToOne
    @JoinColumn(name = "transport_company_id")
    private TransportCompany transportCompany;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlan travelPlan;

    public TravelSegment() {
    }

    public TravelSegment(Destination destination, Accommodation accommodation, Destination followUpDestination,
                         Accommodation followUpAccommodation, TransportCompany transportCompany, TravelPlan travelPlan) {
        this.destination = destination;
        this.accommodation = accommodation;
        this.followUpDestination = followUpDestination;
        this.followUpAccommodation = followUpAccommodation;
        this.transportCompany = transportCompany;
        this.travelPlan = travelPlan;
    }

    public Long getTravelSegmentId() {
        return travelSegmentId;
    }

    public void setTravelSegmentId(Long travelSegmentId) {
        this.travelSegmentId = travelSegmentId;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Destination getFollowUpDestination() {
        return followUpDestination;
    }

    public void setFollowUpDestination(Destination followUpDestination) {
        this.followUpDestination = followUpDestination;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Accommodation getFollowUpAccommodation() {
        return followUpAccommodation;
    }

    public void setFollowUpAccommodation(Accommodation followUpAccommodation) {
        this.followUpAccommodation = followUpAccommodation;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public TravelPlan getTravelPlan() {
        return travelPlan;
    }

    public void setTravelPlan(TravelPlan travelPlan) {
        this.travelPlan = travelPlan;
    }

    @Override
    public String toString() {
        return "TravelSegment{" +
                "travelSegmentId=" + travelSegmentId +
                ", destination=" + destination +
                ", accommodation=" + accommodation +
                ", followUpDestination=" + followUpDestination +
                ", followUpAccommodation=" + followUpAccommodation +
                ", transportCompany=" + transportCompany +
//                ", travelPlan=" + travelPlan +
                "}\n";
    }
}
