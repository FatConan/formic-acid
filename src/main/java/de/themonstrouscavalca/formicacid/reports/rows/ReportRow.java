package de.themonstrouscavalca.formicacid.reports.rows;

public class ReportRow extends AbstractRow{
    public ReportRow(ReportRowBuilder builder){
        this.entries.addAll(builder.cells);
    }
}
