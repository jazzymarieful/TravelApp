package sr.unasat.travelapp.reports;

import sr.unasat.travelapp.entities.*;

import java.util.Set;

public class TextFile implements File {

    private Account account;

    private Set<Traveler> travelers;

    private Set<TravelSegment> travelSegments;

    private TravelPlan travelPlan;

    private TravelGroup travelGroup;

    public TextFile(TravelPackage travelPackage) {
        account = travelPackage.getAccount();
        travelGroup = travelPackage.getTravelGroup();
        travelPlan = travelPackage.getTravelPlan();
        travelers = travelPackage.getTravelGroup().getTravelers();
        travelSegments = travelPackage.getTravelPlan().getTravelSegments();
    }

    @Override
    public void readReport() {
        System.out.println("TEXT REPORT OF TRAVEL PACKAGE");
        System.out.println("The following is a text report of the travel package created by user " + account.getUsername() + ".");
        System.out.println("The user has booked a trip from " + travelPlan.getStartDate() + " until " + travelPlan.getEndDate() +
                " for a duration of " + travelPlan.getDuration() + " days.");
        System.out.println("The travelers on this trip will be " + getTravelerNames() + " for a total of " +
                travelGroup.getTravelerCount() + " travelers.");
        System.out.println("The locations that will be visited are " + getLocations());

        System.out.println("Enjoy your trip ;-) \n");
        System.out.println("----------End of report----------");

    }

    private String getTravelerNames() {
        StringBuilder namesString = new StringBuilder();
        int count = 1;
        for (Traveler traveler : travelers) {
            if (count == travelers.size()) {
                namesString.append(" and " + traveler.getFirstName() + " " + traveler.getLastName());
            } else if (count == 1){
                namesString.append(traveler.getFirstName() + " " + traveler.getLastName());
            } else {
                namesString.append(", " + traveler.getFirstName() + " " + traveler.getLastName());
            }
            count++;
        }
        return namesString.toString();
    }

    private String getLocations() {
        StringBuilder locationsString = new StringBuilder();
        int count = 1;
        for (TravelSegment travelSegment : travelSegments) {
            if (count == travelSegments.size()) {
                locationsString.append(", " + ((travelSegment.getDestination().getCity() != null) ? travelSegment.getDestination().getCity() :
                        travelSegment.getDestination().getLocation()) + ", " + travelSegment.getDestination().getCountry() +
                        " at " + travelSegment.getAccommodation().getName() + " with a " +
                        travelSegment.getAccommodation().getRating() + " star rating");
                locationsString.append(" and " + ((travelSegment.getFollowUpDestination().getCity() != null) ? travelSegment.getFollowUpDestination().getCity() :
                        travelSegment.getFollowUpDestination().getLocation()) + ", " + travelSegment.getFollowUpDestination().getCountry() +
                        " at " + travelSegment.getFollowUpAccommodation().getName() + " with a " +
                        travelSegment.getFollowUpAccommodation().getRating() + " star rating");
            } else if (count == 1){
                locationsString.append(((travelSegment.getDestination().getCity() != null) ? travelSegment.getDestination().getCity() :
                        travelSegment.getDestination().getLocation()) + ", " + travelSegment.getDestination().getCountry() +
                        " where you will be staying at " + travelSegment.getAccommodation().getName() + " with a " +
                        travelSegment.getAccommodation().getRating() + " star rating");
            } else {
                locationsString.append(", " + ((travelSegment.getDestination().getCity() != null) ? travelSegment.getDestination().getCity() :
                        travelSegment.getDestination().getLocation()) + ", " + travelSegment.getDestination().getCountry() +
                         " at " + travelSegment.getAccommodation().getName() + " with a " +
                        travelSegment.getAccommodation().getRating() + " star rating");
            }
            count++;
        }
        return locationsString.toString();
    }


}
