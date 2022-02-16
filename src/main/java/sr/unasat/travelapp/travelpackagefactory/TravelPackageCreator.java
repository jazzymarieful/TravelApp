package sr.unasat.travelapp.travelpackagefactory;

import sr.unasat.travelapp.entities.Account;
import sr.unasat.travelapp.entities.TravelPackage;

public interface TravelPackageCreator {

    public TravelPackage addTravelPackageToDatabase(Account account);

}
