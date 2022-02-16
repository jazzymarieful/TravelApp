package sr.unasat.travelapp.reports;

import sr.unasat.travelapp.reports.File;
import sr.unasat.travelapp.reports.RawReport;
import sr.unasat.travelapp.reports.Report;

public class ReportAdapter implements Report {

    File file;

    public ReportAdapter(File file) {
        this.file = file;
    }

    @Override
    public void displayReport() {
        file.readReport();
    }

}
