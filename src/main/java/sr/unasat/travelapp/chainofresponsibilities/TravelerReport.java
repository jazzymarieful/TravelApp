package sr.unasat.travelapp.chainofresponsibilities;

import sr.unasat.travelapp.dao.TravelerDAO;
import sr.unasat.travelapp.entities.Traveler;

public class TravelerReport implements Chain {

    private Chain nextInChain;

    TravelerDAO travelerDAO = new TravelerDAO();

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void getReport(String request) {
        if (request.equals("Traveler report")) {
            System.out.println("TRAVELER REPORT");
            System.out.println(travelerDAO.countTravelers() + " people have travelled with us");
            int count = 1;
            for (Traveler traveler : travelerDAO.findAllTravelers()) {
                System.out.println(count + ": " + traveler.getFirstName() + ", " + traveler.getLastName() + ", " +
                        traveler.getPassport() + ", " + traveler.getAge());
                count++;
            }
        } else {
            nextInChain.getReport(request);
        }
    }


}
