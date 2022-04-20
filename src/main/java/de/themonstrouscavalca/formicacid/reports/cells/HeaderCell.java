package de.themonstrouscavalca.formicacid.reports.cells;

import play.twirl.api.Html;

public class HeaderCell extends ReportCell{
    public HeaderCell(ReportCellBuilder builder){
        super(builder);
    }

    @Override
    public Html render(){
        return de.themonstrouscavalca.formicacid.twirl.reports.html.headerCell.render(this);
    }
}
