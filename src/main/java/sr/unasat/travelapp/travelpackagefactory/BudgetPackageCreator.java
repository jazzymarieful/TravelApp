package sr.unasat.travelapp.travelpackagefactory;

import sr.unasat.travelapp.dao.TravelGroupDAO;
import sr.unasat.travelapp.dao.TravelPackageDAO;
import sr.unasat.travelapp.dao.TravelPlanDAO;
import sr.unasat.travelapp.entities.Account;
import sr.unasat.travelapp.entities.TravelPackage;

public class BudgetPackageCreator implements TravelPackageCreator{

    private TravelPackageDAO travelPackageDAO = new TravelPackageDAO();
    private TravelGroupDAO travelGroupDAO = new TravelGroupDAO();
    private TravelPlanDAO travelPlanDAO = new TravelPlanDAO();

    @Override
    public TravelPackage addTravelPackageToDatabase(Account account) {
        System.out.println("Travel Package had been added to database");
        return travelPackageDAO.insertTravelPackage(new TravelPackage(travelGroupDAO.findLastTravelGroupRecord(),
                travelPlanDAO.findLastTravelPlanRecord(), account));
    }
}
