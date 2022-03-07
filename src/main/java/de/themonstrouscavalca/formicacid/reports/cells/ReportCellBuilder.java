package de.themonstrouscavalca.formicacid.reports.cells;

import de.themonstrouscavalca.formicacid.reports.rows.ReportRowBuilder;
import de.themonstrouscavalca.formicacid.reports.interfaces.IReportCell;
import de.themonstrouscavalca.formicacid.templates.ButtonConfig;

import java.util.ArrayList;
import java.util.List;

public class ReportCellBuilder{
    private final ReportRowBuilder parent;
    private final boolean isHeader;

    private Object value = "";
    private int colSpan = 1;
    private String link = null;
    private List<ButtonConfig> buttonConfigs = new ArrayList<>();

    public ReportCellBuilder(ReportRowBuilder parent){
        if(parent != null){
            this.parent = parent;
            this.isHeader = parent.isHeader();
        }else{
            this.parent = null;
            this.isHeader = false;
        }
    }

    public ReportCellBuilder(){
        this.parent = null;
        this.isHeader = false;
    }

    public Object getValue(){
        return value;
    }

    public ReportCellBuilder setValue(Object value){
        this.value = value;
        return this;
    }

    public ReportCellBuilder addButton(ButtonConfig config){
        this.buttonConfigs.add(config);
        return this;
    }

    public int getColSpan(){
        return colSpan;
    }

    public ReportCellBuilder setColSpan(int colSpan){
        this.colSpan = colSpan;
        return this;
    }

    public String getLink(){
        return link;
    }

    public ReportCellBuilder setLink(String link){
        this.link = link;
        return this;
    }

    public ReportCellBuilder hidden(){
        this.setColSpan(0);
        return this;
    }

    public List<ButtonConfig> getButtonConfigs(){
        return buttonConfigs;
    }

    public ReportCellBuilder setButtonConfigs(List<ButtonConfig> buttonConfigs){
        this.buttonConfigs = buttonConfigs;
        return this;
    }

    public ReportRowBuilder end(){
        return this.parent.addCell(this.build());
    }

    public IReportCell build(){
        IReportCell cell;
        if(this.isHeader){
            cell = new HeaderCell(this);
        }else if(!this.buttonConfigs.isEmpty()){
            cell = new ActionCell(this);
        }else{
            cell = new ReportCell(this);
        }
        return cell;
    }
}
