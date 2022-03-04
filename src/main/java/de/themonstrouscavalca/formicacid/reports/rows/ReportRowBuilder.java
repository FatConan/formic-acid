package de.themonstrouscavalca.formicacid.reports.rows;

import de.themonstrouscavalca.formicacid.reports.ReportSectionBuilder;
import de.themonstrouscavalca.formicacid.reports.cells.ReportCell;
import de.themonstrouscavalca.formicacid.reports.cells.ReportCellBuilder;
import de.themonstrouscavalca.formicacid.reports.interfaces.IReportCell;
import de.themonstrouscavalca.formicacid.reports.interfaces.IReportRow;

import java.util.ArrayList;
import java.util.List;

public class ReportRowBuilder{
    final ReportSectionBuilder parent;
    boolean isHeader = false;
    final List<IReportCell> cells = new ArrayList<>();

    public ReportRowBuilder(ReportSectionBuilder sectionBuilder){
        this.parent = sectionBuilder;
    }

    public ReportCellBuilder newCell(){
        return new ReportCellBuilder(this);
    }

    public ReportRowBuilder addSpacer(){
        this.cells.add(ReportCell.spacer());
        return this;
    }

    public ReportRowBuilder addSpacer(int colSpan){
        this.cells.add(ReportCell.spacer(colSpan));
        return this;
    }

    public ReportRowBuilder addSimpleCells(List<String> values){
        for(String val: values){
            this.newCell().setValue(val).end();
        }
        return this;
    }

    public ReportRowBuilder addSimpleCell(String value){
        this.newCell().setValue(value).end();
        return this;
    }

    public ReportRowBuilder addCell(IReportCell cell){
       this.cells.add(cell);
       return this;
    }

    public ReportRowBuilder makeHeader(){
        this.isHeader = true;
        return this;
    }

    public boolean isHeader(){
        return this.isHeader;
    }

    public ReportSectionBuilder end(){
        return this.parent.addRow(this.build());
    }

    public IReportRow build(){
        if(this.isHeader){
            return new HeaderRow(this);
        }else{
            return new ReportRow(this);
        }
    }
}
