package de.themonstrouscavalca.formicacid.reports;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportRow;

import java.util.ArrayList;
import java.util.List;

public class Report{
    private ReportSection defaultSection;
    private List<ReportSection> reportSections = new ArrayList<>();

    public Report(ReportBuilder builder){
        this.defaultSection = builder.defaultSection;
        this.reportSections.add(this.defaultSection);
        this.reportSections.addAll(builder.sections);
    }

    public List<ReportSection> getReportSections(){
        return this.reportSections;
    }

    public List<IReportRow> getData(){
        return this.defaultSection.getData();
    }

    public List<IReportRow> getHeaders(){
        return  this.defaultSection.getHeaders();
    }

    public List<IReportRow> getBody(){
        return this.defaultSection.getBody();
    }
}
