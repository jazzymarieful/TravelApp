package sr.unasat.travelapp.reports;

import sr.unasat.travelapp.entities.*;

import java.util.Set;

public class RawReport implements Report {

    private Set<Traveler> travelers;

    private Set<TravelSegment> travelSegments;

    private TravelPlan travelPlan;

    private TravelGroup travelGroup;

    private TravelPackage travelPackage;

    public RawReport(TravelPackage travelPackage) {
        travelGroup = travelPackage.getTravelGroup();
        travelPlan = travelPackage.getTravelPlan();
        travelers = travelPackage.getTravelGroup().getTravelers();
        travelSegments = travelPackage.getTravelPlan().getTravelSegments();

        this.travelPackage = travelPackage;
    }

    @Override
    public void displayReport() {
        System.out.println("RAW REPORT OF TRAVEL PACKAGE");
        System.out.println("Travel group in travel package:");
        System.out.println(travelGroup);
        System.out.println("Travelers in travel package:");
        System.out.println(travelers);
        System.out.println();
        System.out.println("Travel plan in travel package:");
        System.out.println(travelPlan);
        System.out.println();
        System.out.println("Travel segments in travel package:");
        System.out.println(travelSegments);
        System.out.println("----------End of report----------");
    }
}
