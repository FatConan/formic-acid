package de.themonstrouscavalca.formicacid.reports.cells;

import de.themonstrouscavalca.formicacid.reports.interfaces.IReportCell;

public class ReportCell implements IReportCell{
    private static final String SPACE = " ";
    private static final ReportCell SPACER = new ReportCell(SPACE, 0, null);

    private final Object value;
    private final String valueText;
    private final int colSpan;
    private final String linkText;

    private ReportCell(Object value, int colSpan, String linkText){
        this.value = value;
        this.valueText = value.toString();
        this.colSpan = colSpan;
        this.linkText = linkText;
    }

    public static ReportCell of(String value){
        return new ReportCell(value, 1, null);
    }

    public static ReportCell of(String value, int colSpan){
        return new ReportCell(value, colSpan, null);
    }

    public static ReportCell of(String value, int colSpan, String linkText){
        return new ReportCell(value, colSpan, linkText);
    }

    public static ReportCell spacer(){
        //Xero width spacer
        return SPACER;
    }

    public static ReportCell spacer(int colSpan){
        return ReportCell.of(SPACE, colSpan);
    }

    @Override
    public Object getValue(){
        return value;
    }

    @Override
    public int getColSpan(){
        return colSpan;
    }

    @Override
    public boolean shouldRender(){
        return this.colSpan > 0;
    }

    @Override
    public String getText(){
        return this.valueText;
    }

    public boolean isLink(){
        return this.linkText != null;
    }

    public String getLinkText(){
        return this.linkText != null ? this.linkText : "";
    }
}
