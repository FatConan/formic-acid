package de.themonstrouscavalca.formicacid.reports.interfaces;

import play.twirl.api.Html;

public interface IReportCell{
    int getColSpan();
    boolean shouldRender();
    boolean isLink();
    Object getValue();
    String getText();
    String getLabel();
    Html render();
}
