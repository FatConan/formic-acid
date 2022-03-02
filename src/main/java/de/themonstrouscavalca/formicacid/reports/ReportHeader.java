package de.themonstrouscavalca.formicacid.reports;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportCell;

import java.util.Arrays;
import java.util.List;

public class ReportHeader extends ReportRow{
    public ReportHeader(IReportCell ... values){
        super(values);
    }

    public ReportHeader(List<IReportCell> values){
        super(values);
    }

    public static ReportHeader of(IReportCell... values){
        ReportHeader report = new ReportHeader();
        report.entries.addAll(Arrays.asList(values));
        return report;
    }

    public static ReportHeader of(List<IReportCell> values){
        ReportHeader report = new ReportHeader();
        report.entries.addAll(values);
        return report;
    }
}
