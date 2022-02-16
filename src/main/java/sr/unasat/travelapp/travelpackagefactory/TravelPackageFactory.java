package sr.unasat.travelapp.travelpackagefactory;

import sr.unasat.travelapp.travelpackagefactory.*;

public class TravelPackageFactory {

    public static TravelGroupCreator createTravelGroup(int type) {
        TravelGroupCreator travelGroupCreator = null;
        switch (type) {
            case 1:
                travelGroupCreator = new TourGroupCreator();
                break;
        }
        return travelGroupCreator;
    }

    public static TravelPlanCreator createTravelPlan(int type) {
        TravelPlanCreator travelPlanCreator = null;
        switch (type) {
            case 1:
                travelPlanCreator = new TourPlanCreator();
                break;
        }
        return travelPlanCreator;
    }

    public static TravelPackageCreator createTravelPackage(int type) {
        TravelPackageCreator travelPackageCreator = null;
        switch (type) {
            case 1:
                travelPackageCreator = new TourPackageCreator();
                break;
        }
        return travelPackageCreator;
    }

}
