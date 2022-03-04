package de.themonstrouscavalca.formicacid.reports.interfaces;

public interface IReportCell{
    int getColSpan();
    boolean shouldRender();
    boolean isLink();
    Object getValue();
    String getText();
}
