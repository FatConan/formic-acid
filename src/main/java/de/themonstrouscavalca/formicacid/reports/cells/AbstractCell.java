package de.themonstrouscavalca.formicacid.reports.cells;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportCell;

public abstract class AbstractCell implements IReportCell{
    private final int colSpan;
    private final Object value;

    AbstractCell(Object value, int colSpan){
        this.colSpan = colSpan;
        this.value = value;
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
}
