package sr.unasat.travelapp.chainofresponsibilities;

public class AdminReportHandler {

    Chain chain1 = new TravelerReport();
    Chain chain2 = new DestinationReport();
    Chain chain3 = new TravelPeriodReport();

    AdminReport adminReport = new AdminReport();

    public AdminReportHandler() {
        chain1.setNextChain(chain2);
        chain2.setNextChain(chain3);
    }

    public void startHandling(int reportSelected) {
        String reportType = adminReport.getReportList().get(reportSelected - 1);
        chain1.getReport(reportType);
    }

}
