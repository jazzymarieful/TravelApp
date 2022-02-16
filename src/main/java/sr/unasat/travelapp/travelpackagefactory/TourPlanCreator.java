package sr.unasat.travelapp.travelpackagefactory;

import sr.unasat.travelapp.dao.*;
import sr.unasat.travelapp.entities.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TourPlanCreator implements TravelPlanCreator {

    private List<Destination> retrievedDestinationList;
    private List<Destination> selectedDestinationList = new ArrayList<>();
    private List<Accommodation> selectedAccommodationList = new ArrayList<>();
    private List<TransportCompany> selectedTransportCompanyList = new ArrayList<>();
    private TravelPlan currentTravelPlan;

    private Scanner scanner = new Scanner(System.in);

    private TravelSegmentDAO travelSegmentDAO = new TravelSegmentDAO();
    private TravelPlanDAO travelPlanDAO = new TravelPlanDAO();
    private DestinationDAO destinationDAO = new DestinationDAO();
    private AccomodationDAO accomodationDAO = new AccomodationDAO();
    private TransportCompanyDAO transportCompanyDAO = new TransportCompanyDAO();

    public void addTravelPlanToDatabase() {
        retrievedDestinationList = destinationDAO.retrieveDestinationList();
//        retrievedDestinationList.forEach(System.out::println);

        System.out.println("Choose destinations in order of visit (up to 10 destinations)");
        chooseDestinationByOrder();
//        selectedDestinationList.forEach(System.out::println);

        System.out.println("Choose start date and end date of travel (up to 31 days of travel)");
        chooseTravelDuration();

        System.out.println("Choose rating of trip (between 1 and 5 stars)");
        chooseAccommodationForEachDestination();
//        selectedAccommodationList.forEach(System.out::println);
        chooseTransportCompanyBetweenDestinations();
//        selectedTransportCompanyList.forEach(System.out::println);

        constructSegments();

        System.out.println("Travel plan has been added to database \n");
    }

    public void chooseDestinationByOrder() {
        int count = 1;
        for (Destination destination : retrievedDestinationList) {
            System.out.println(count + ": " + destination.getCountry() + ", " + destination.getCity() + ", " + destination.getLocation());
            count++;
        }
        int[] destinationSelection = new int[10];
        int selectionCount = 0;
        while (selectionCount != destinationSelection.length) {
            System.out.println("Choose destination by number or enter 0 to finish selection");
            int selectedDestination = scanner.nextInt();
            if (selectedDestination >= 0 && selectedDestination < count) {
                if (selectedDestination != 0) {
                    destinationSelection[selectionCount] = selectedDestination;
                    selectionCount++;
                } else {
                    break;
                }
            } else {
                System.out.println("Selection incorrect, choose one of the corresponding numbers");
            }
        }
        System.out.println("Selection has been finished");

        for (int index : destinationSelection) {
            if (index == 0) {
                break;
            }
            selectedDestinationList.add(retrievedDestinationList.get(index - 1));
        }
        System.out.println("Destination selection has been made");
    }

    public void chooseTravelDuration() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int duration;
        do {
            System.out.println("Enter start date: (dd/mm/yyyy)");
            LocalDate startDate = LocalDate.parse(scanner.next(), formatter);
            System.out.println("Enter end date: (dd/mm/yyyy)");
            LocalDate endDate = LocalDate.parse(scanner.next(), formatter);

            duration = (int) ChronoUnit.DAYS.between(startDate, endDate);

            if (duration >= 1 && duration <= 31) {
                System.out.println("Travel duration has been chosen");
                currentTravelPlan = travelPlanDAO.insertTravelPlan(new TravelPlan(startDate, endDate, duration));
            } else {
                System.out.println("Incorrect travel duration chosen (up to 31 days)");
            }
        } while (duration < 1 || duration > 31);
    }

    public void chooseAccommodationForEachDestination() {
        int rating;
        do {
            rating = scanner.nextInt();
            if (rating >= 1 && rating <=5) {
                for (Destination destination : selectedDestinationList) {
                    selectedAccommodationList.add(accomodationDAO.findAccomodationByDestinationAndRating(destination.getDestinationId(), rating));
                }
                System.out.println("Accommodation selection has been made");
            } else {
                System.out.println("Incorrect rating chosen (between 1 and 5 stars)");
            }
        } while (rating < 1 || rating > 5);
    }

    public void chooseTransportCompanyBetweenDestinations() {
        for (int index = 0; index < selectedDestinationList.size() - 1; index++) {
            selectedTransportCompanyList.add(transportCompanyDAO.findTransportCompanyBetweenDestinations(selectedDestinationList.get(index).getDestinationId(),
                    selectedDestinationList.get(index + 1).getDestinationId()));
        }
    }

    public void constructSegments() {
//        TravelPlan lastTravelPlan = travelPlanDAO.findLastTravelPlanRecord();
        for (int count = 0; count < selectedDestinationList.size() - 1; count++) {
            travelSegmentDAO.insertTravelSegment(new TravelSegment(selectedDestinationList.get(count),selectedAccommodationList.get(count),
                    selectedDestinationList.get(count + 1), selectedAccommodationList.get(count + 1), selectedTransportCompanyList.get(count),
                    currentTravelPlan));
        }
    }

}
