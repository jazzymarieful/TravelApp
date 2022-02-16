package sr.unasat.travelapp.application;

import sr.unasat.travelapp.config.JPAConfiguration;
import sr.unasat.travelapp.services.*;

import javax.persistence.EntityManager;

public class App {

    public static void main(String[] args) {

//        EntityManager entityManager = JPAConfiguration.getEntityManager();

        LoginService loginService = new LoginService();
        loginService.login();

//        TravelGroupDAO travelGroupDAO = new TravelGroupDAO();
//        System.out.println(travelGroupDAO.findAllTravelGroups());
//        System.out.println(travelGroupDAO.findTravelGroupByTraveler("389ht"));

//        TravelSegmentDAO travelSegmentDAO = new TravelSegmentDAO();
//        System.out.println(travelSegmentDAO.findAllTravelSegments());
//        System.out.println(travelSegmentDAO.retrieveTravelSegmentByDestinationFrequency());
//
//        TravelPackageDAO travelPackageDAO = new TravelPackageDAO();
//        RawReport rawReport = new RawReport(travelPackageDAO.retrieveLastTravelPackageReport());
//        rawReport.displayReport();


        JPAConfiguration.shutdown();








    }
}
