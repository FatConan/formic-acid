package de.themonstrouscavalca.formicacid.reports;

import de.themonstrouscavalca.formicacid.reports.builder.ReportBuilder;

import java.util.ArrayList;
import java.util.List;

public class Report{
    private ReportSection defaultSection = new ReportSection();
    private List<ReportSection> reportSections = new ArrayList<>();

    public Report(){
        this.reportSections.add(this.defaultSection);
    }

    public Report(ReportBuilder builder){

    }


    public List<ReportSection> getReportSections(){
        return this.reportSections;
    }

    public void addSection(ReportSection section){
        this.reportSections.add(section);
    }

    public void addRow(ReportRow row){
        this.defaultSection.addRow(row);
    }

    public List<ReportRow> getData(){
        return this.defaultSection.getData();
    }

    public List<ReportRow> getHeaders(){
        return  this.defaultSection.getHeaders();
    }

    public List<ReportRow> getBody(){
        return this.defaultSection.getBody();
    }
}
