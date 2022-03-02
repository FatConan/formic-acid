package de.themonstrouscavalca.formicacid.reports.builder;

import de.themonstrouscavalca.formicacid.reports.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder{
    private ReportSectionBuilder defaultSectionBuilder = new ReportSectionBuilder(this, "");
    private List<ReportSectionBuilder> reportSectionBuilders = new ArrayList<>();


    public ReportSectionBuilder addSection(String sectionName){
        return new ReportSectionBuilder(this, sectionName);
    }

    public ReportRowBuilder addRow(){
        return new ReportRowBuilder(this.defaultSectionBuilder);
    }

    public ReportRowBuilder addHeaderRow(){
        ReportRowBuilder rowBuilder = new ReportRowBuilder(this.defaultSectionBuilder);
        return rowBuilder.makeHeader();
    }

    public Report build(){
        return new Report(this);
    }


}
