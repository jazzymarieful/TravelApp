package sr.unasat.travelapp.chainofresponsibilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminReport {

    private static final List<String> reportList = new ArrayList<>(Arrays.asList("Traveler report", "Destination report", "Period report"));
//
//    public AdminReport(String typeOfReport) {
//        this.typeOfReport = typeOfReport;
//    }

    public List<String> getReportList() {
        return reportList;
    }
}
