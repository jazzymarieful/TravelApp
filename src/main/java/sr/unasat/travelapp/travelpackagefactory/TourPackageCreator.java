package sr.unasat.travelapp.travelpackagefactory;

import sr.unasat.travelapp.config.JPAConfiguration;
import sr.unasat.travelapp.dao.TravelGroupDAO;
import sr.unasat.travelapp.dao.TravelPackageDAO;
import sr.unasat.travelapp.dao.TravelPlanDAO;
import sr.unasat.travelapp.entities.Account;
import sr.unasat.travelapp.entities.TravelPackage;

public class TourPackageCreator implements TravelPackageCreator {

    private TravelPackage travelPackage;

    private TravelPackageDAO travelPackageDAO = new TravelPackageDAO();
    private TravelGroupDAO travelGroupDAO = new TravelGroupDAO();
    private TravelPlanDAO travelPlanDAO = new TravelPlanDAO();

    public TravelPackage addTravelPackageToDatabase(Account account) {
        System.out.println("Travel Package has been added to database");
        travelPackage = travelPackageDAO.insertTravelPackage(new TravelPackage(travelGroupDAO.findLastTravelGroupRecord(),
                travelPlanDAO.findLastTravelPlanRecord(), account));
        return travelPackage;
    }

}
