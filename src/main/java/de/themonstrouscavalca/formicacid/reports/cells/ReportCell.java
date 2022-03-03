package de.themonstrouscavalca.formicacid.reports.cells;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportCell;

public class ReportCell extends AbstractCell{
    private static final String SPACE = " ";
    private static final ReportCell SPACER = new ReportCell(SPACE, 0, null);

    private final String linkText;

    ReportCell(Object value, int colSpan, String linkText){
        super(value, colSpan);
        this.linkText = linkText;
    }

    public static ReportCell spacer(){
        //Zero width spacer
        return SPACER;
    }

    public static ReportCell spacer(int colSpan){
        return new ReportCell(SPACE, colSpan, null);
    }

    public boolean isLink(){
        return this.linkText != null;
    }

    public String getLinkText(){
        return this.linkText != null ? this.linkText : "";
    }
}
