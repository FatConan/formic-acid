package de.themonstrouscavalca.formicacid.reports.interfaces;

public interface IReportCell{
    int getColSpan();
    boolean shouldRender();
    Object getValue();
    String getText();
}
