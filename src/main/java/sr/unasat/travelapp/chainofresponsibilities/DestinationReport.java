package sr.unasat.travelapp.chainofresponsibilities;

import sr.unasat.travelapp.dao.TravelSegmentDAO;
import sr.unasat.travelapp.entities.Destination;
import sr.unasat.travelapp.entities.TravelSegment;

import java.util.List;

public class DestinationReport implements Chain {

    private Chain nextInChain;

    TravelSegmentDAO travelSegmentDAO = new TravelSegmentDAO();

    List<TravelSegment> travelSegmentList;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void getReport(String request) {
        if (request.equals("Destination report")) {
            travelSegmentList = travelSegmentDAO.retrieveTravelSegmentByDestinationFrequency();
            System.out.println("The destinations travelled to from most to least are as follows: ");
            for (TravelSegment travelSegment : travelSegmentList) {
                System.out.println(travelSegment.getDestination().getCountry() + ", " + travelSegment.getDestination().getCity() +
                        ", " + travelSegment.getDestination().getLocation());
            }
            Destination mostTravelSegmentDestination = travelSegmentList.get(travelSegmentList.size()-1).getDestination();
            Destination leastTravelSegmentDestination = travelSegmentList.get(0).getDestination();
            System.out.println();
            System.out.println("The destination most travelled to is " + mostTravelSegmentDestination.getCountry() + ", " +
                    mostTravelSegmentDestination.getCity() + ", " + mostTravelSegmentDestination.getLocation());
            System.out.println("The destination least travelled to is " + leastTravelSegmentDestination.getCountry() + ", " +
                    leastTravelSegmentDestination.getCity() + ", " + leastTravelSegmentDestination.getLocation());
        } else {
            nextInChain.getReport(request);
        }
    }

}
