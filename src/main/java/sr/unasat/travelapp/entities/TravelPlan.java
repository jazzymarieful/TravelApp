package sr.unasat.travelapp.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "travel_plan")
public class TravelPlan {

    @Id
    @Column(name = "travel_plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelPlanId;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Column(name = "duration", nullable = false)
    private int duration;

    @OneToMany(mappedBy = "travelPlan", fetch = FetchType.EAGER)
    private Set<TravelSegment> travelSegments;

    public TravelPlan() {
    }

    public TravelPlan(LocalDate startDate, LocalDate endDate, int duration) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
    }

    public Long getTravelPlanId() {
        return travelPlanId;
    }

    public void setTravelPlanId(Long travelPlanId) {
        this.travelPlanId = travelPlanId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<TravelSegment> getTravelSegments() {
        return travelSegments;
    }

    public void setTravelSegments(Set<TravelSegment> travelSegments) {
        this.travelSegments = travelSegments;
    }

    @Override
    public String toString() {
        return "TravelPlan{" +
                "travelPlanId=" + travelPlanId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                '}';
    }
}
