package de.themonstrouscavalca.formicacid.reports.cells;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportCell;
import play.twirl.api.Html;

/**
 *
 */
public abstract class AbstractCell implements IReportCell{
    private final int colSpan;
    private final Object value;

    AbstractCell(ReportCellBuilder builder){
        this.colSpan = builder.getColSpan();
        this.value = builder.getValue();
    }

    @Override
    public int getColSpan(){
        return this.colSpan;
    }

    @Override
    public boolean shouldRender(){
        return this.colSpan > 0;
    }

    @Override
    public Object getValue(){
        return this.value;
    }

    @Override
    public String getText(){
        return this.value.toString();
    }

    @Override
    public boolean isLink(){
        return false;
    }
}
