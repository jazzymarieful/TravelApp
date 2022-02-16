package sr.unasat.travelapp.travelpackagefactory;

import sr.unasat.travelapp.dao.TravelGroupDAO;
import sr.unasat.travelapp.dao.TravelerDAO;
import sr.unasat.travelapp.entities.TravelGroup;
import sr.unasat.travelapp.entities.Traveler;
import sr.unasat.travelapp.utilities.RandomGenerator;

import java.util.Scanner;

public class BudgetGroupCreator implements TravelGroupCreator{

    private TravelGroup currentTravelGroup;
    private boolean isSuccessful;

    private Scanner scanner = new Scanner(System.in);

    private TravelerDAO travelerDAO = new TravelerDAO();
    private TravelGroupDAO travelGroupDAO = new TravelGroupDAO();
    private RandomGenerator rg = new RandomGenerator();

    @Override
    public void addTravelGroupToDatabase() {
        int travelerCount;
        while (!isSuccessful) {
            System.out.println("Choose number of travelers on trip (up to 2 travelers)");
            travelerCount = scanner.nextInt();

            if (travelerCount >=1 && travelerCount <=2) {
                System.out.println("Number of travelers chosen is " + travelerCount);
                currentTravelGroup = travelGroupDAO.insertTravelGroup(new TravelGroup(travelerCount));

                int travelerCountTotal = travelerCount + 1;
                while (travelerCount != 0) {
                    System.out.println("Enter information of traveler no. " + (travelerCountTotal - travelerCount));
                    travelerInput();
                    travelerCount--;
                }
                System.out.println("Travel group has been added to database \n");
                isSuccessful = true;
            } else {
                System.out.println("Incorrect number of travelers entered \nChoose between 1 and 2");
            }
        }

    }

    @Override
    public void travelerInput() {
        System.out.println("Enter first name: ");
        String firstName = scanner.next();
        System.out.println("Enter last name: ");
        String lastName = scanner.next();
        System.out.println("Enter passportNumber: ");
        String passport = scanner.next();
        System.out.println("Enter age: ");
        int age = scanner.nextInt();
//        travelerDAO.insertTraveler(new Traveler(rg.getRName(), rg.getRLName(), rg.getRPass(), rg.getRAge(), travelGroupDAO.findLastTravelGroupRecord()));
        travelerDAO.insertTraveler(new Traveler(firstName, lastName, passport, age, currentTravelGroup));
    }
}
