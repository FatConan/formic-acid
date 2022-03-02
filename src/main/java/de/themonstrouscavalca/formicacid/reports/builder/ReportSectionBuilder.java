package de.themonstrouscavalca.formicacid.reports.builder;

import java.util.ArrayList;
import java.util.List;

public class ReportSectionBuilder{
    private final ReportBuilder reportBuilder;
    private final String sectionName;
    private List<ReportRowBuilder> rowBuilders = new ArrayList<>();

    public ReportSectionBuilder(ReportBuilder reportBuilder, String sectionName){
        this.reportBuilder = reportBuilder;
        this.sectionName = sectionName;
    }

    public ReportRowBuilder addRow(){
        return new ReportRowBuilder(this);
    }

    ReportSectionBuilder addRowBuilder(ReportRowBuilder rowBuilder){
        this.rowBuilders.add(rowBuilder);
        return this;
    }
}
