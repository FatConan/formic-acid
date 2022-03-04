package de.themonstrouscavalca.formicacid.reports.rows;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportCell;
import de.themonstrouscavalca.formicacid.reports.interfaces.IReportRow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRow implements IReportRow{
    protected List<IReportCell> entries = new ArrayList<>();

    @Override
    public String [] getData(){
        String [] values = new String[this.entries.size()];
        this.entries.stream().map(IReportCell::getText).collect(Collectors.toList()).toArray(values);
        return values;
    }

    @Override
    public List<IReportCell> getDataColumns(){
        return this.entries;
    }

    @Override
    public List<IReportCell> getVisibleDataColumns(){
        return this.entries.stream().filter(IReportCell::shouldRender).collect(Collectors.toList());
    }
}
