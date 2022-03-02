package de.themonstrouscavalca.formicacid.reports;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportCell;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class ReportRow {
    protected final List<IReportCell> entries = new ArrayList<>();

    public ReportRow(IReportCell... values){
        this.entries.addAll(Arrays.asList(values));
    }

    public ReportRow(List<IReportCell> values){
        this.entries.addAll(values);
    }

    private ReportRow(){

    }

    public static ReportRow of(IReportCell... values){
        ReportRow report = new ReportRow();
        report.entries.addAll(Arrays.asList(values));
        return report;
    }

    public static ReportRow of(List<IReportCell> values){
        ReportRow report = new ReportRow();
        report.entries.addAll(values);
        return report;
    }

    public static String timeToHoursMinutes(Double hours){
        double minutesPart = hours % 1;
        double hoursPart = hours - minutesPart;
        DecimalFormat decim = new DecimalFormat("00");
        return String.format("%.0f:%s", hoursPart, decim.format(abs(60 * minutesPart)));
    }

    public String [] getData(){
        String [] values = new String[this.entries.size()];
        this.entries.stream().map(IReportCell::getText).collect(Collectors.toList()).toArray(values);
        return values;
    }

    public List<IReportCell> getDataColumns(){
        return this.entries;
    }

    public List<IReportCell> getVisibleDataColumns(){
        return this.entries.stream().filter(IReportCell::shouldRender).collect(Collectors.toList());
    }
}
