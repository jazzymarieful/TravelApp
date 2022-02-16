package sr.unasat.travelapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "traveler")
public class Traveler {

    @Id
    @Column(name = "traveler_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelerId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "passport", nullable = false, unique = true)
    private String passport;
    @Column(name = "age")
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_group_id", nullable = false)
    private TravelGroup travelGroup;

    public Traveler() {
    }

    public Traveler(String firstName, String lastName, String passport, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.age = age;
    }

    public Traveler(Long travelerId, String firstName, String lastName, String passport, int age) {
        this.travelerId = travelerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.age = age;
    }

    public Traveler(String firstName, String lastName, String passport, int age, TravelGroup travelGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.age = age;
        this.travelGroup = travelGroup;
    }

    public Long getTravelerId() {
        return travelerId;
    }

    public void setTravelerId(Long travelerId) {
        this.travelerId = travelerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Traveler{" +
                "travelerId=" + travelerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                ", age=" + age +
                "}\n";
    }
}
