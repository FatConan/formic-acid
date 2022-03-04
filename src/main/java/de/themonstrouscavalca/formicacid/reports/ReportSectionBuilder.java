package de.themonstrouscavalca.formicacid.reports;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportRow;
import de.themonstrouscavalca.formicacid.reports.rows.ReportRow;
import de.themonstrouscavalca.formicacid.reports.rows.ReportRowBuilder;

import java.util.ArrayList;
import java.util.List;

public class ReportSectionBuilder{
    final ReportBuilder reportBuilder;
    final String sectionName;
    List<IReportRow> rows = new ArrayList<>();

    public ReportSectionBuilder(ReportBuilder reportBuilder, String sectionName){
        this.reportBuilder = reportBuilder;
        this.sectionName = sectionName;
    }

    public ReportRowBuilder newRow(){
        return new ReportRowBuilder(this);
    }

    public ReportRowBuilder newHeaderRow(){
        ReportRowBuilder rowBuilder = new ReportRowBuilder(this);
        return rowBuilder.makeHeader();
    }

    public ReportSectionBuilder addRow(IReportRow row){
        this.rows.add(row);
        return this;
    }

    public ReportSection build(){
        return new ReportSection(this);
    }

    public ReportBuilder end(){
        return this.reportBuilder.addSection(this.build());
    }
}
