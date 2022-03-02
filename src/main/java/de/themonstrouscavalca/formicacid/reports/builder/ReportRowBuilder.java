package de.themonstrouscavalca.formicacid.reports.builder;

public class ReportRowBuilder{
    private final ReportSectionBuilder sectionBuilder;
    private boolean isHeader = false;

    public ReportRowBuilder(ReportSectionBuilder sectionBuilder){
        this.sectionBuilder = sectionBuilder;
        this.sectionBuilder.addRowBuilder(this);
    }

    public ReportRowBuilder makeHeader(){
        this.isHeader = true;
        return this;
    }
}
