package de.themonstrouscavalca.formicacid.reports.cells;

public class ReportCell extends AbstractCell{
    private static final String SPACE = " ";
    private static final ReportCell SPACER = (ReportCell) new ReportCellBuilder().setValue(SPACE).setColSpan(0).build();

    private final String linkText;

    public ReportCell(ReportCellBuilder builder){
        super(builder);
        this.linkText = builder.getLink();
    }

    public static ReportCell spacer(){
        //Zero width spacer
        return SPACER;
    }

    public static ReportCell spacer(int colSpan){
        return (ReportCell) new ReportCellBuilder().setValue(SPACE).setColSpan(colSpan).build();
    }

    @Override
    public boolean isLink(){
        return this.linkText != null;
    }

    public String getLinkText(){
        return this.linkText != null ? this.linkText : "";
    }
}
