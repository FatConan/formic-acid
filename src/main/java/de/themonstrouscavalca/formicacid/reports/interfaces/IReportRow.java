package de.themonstrouscavalca.formicacid.reports.interfaces;

import java.util.List;

public interface IReportRow{
    String [] getData();
    List<IReportCell> getDataColumns();
    List<IReportCell> getVisibleDataColumns();
}
