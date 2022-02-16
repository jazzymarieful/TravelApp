package sr.unasat.travelapp.chainofresponsibilities;

import sr.unasat.travelapp.dao.TravelPlanDAO;
import sr.unasat.travelapp.entities.TravelPlan;

import java.time.Month;
import java.util.*;

public class TravelPeriodReport implements Chain {

    private Chain nextInChain;

    TravelPlanDAO travelPlanDAO = new TravelPlanDAO();

    List<TravelPlan> travelPlanList;
    Map<Month, Integer> travelPlanMonthList = new HashMap<>();

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void getReport(String request) {
        if (request.equals("Period report")) {
            System.out.println("the travel periods are...");
            travelPlanList = travelPlanDAO.retrieveTravelPlans();

            for (int index = 0; index < travelPlanList.size(); index++) {
                Month key = travelPlanList.get(index).getStartDate().getMonth();
                if (travelPlanMonthList.containsKey(key)) {
                    int count = travelPlanMonthList.get(key);
                    count++;
                    travelPlanMonthList.put(key, count);

                } else {
                    travelPlanMonthList.put(key, 1);
                }
            }

            TreeMap<Month, Integer> sorted = new TreeMap<>(travelPlanMonthList);
            Set<Map.Entry<Month, Integer>> mappings = sorted.entrySet();

            System.out.println("The following is a list of amount of travel by month");

            for (Map.Entry<Month, Integer> val : mappings) {
                System.out.println("The month " + val.getKey() + " is travelled " + val.getValue() + " times");
            }
        } else {
            System.out.println("Incorrect request given");
        }
    }

}
