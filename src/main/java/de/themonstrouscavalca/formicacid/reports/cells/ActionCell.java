package de.themonstrouscavalca.formicacid.reports.cells;

import de.themonstrouscavalca.formicacid.templates.ButtonConfig;

import java.util.List;

public class ActionCell extends AbstractCell{
    private final List<ButtonConfig> configurations;

    public ActionCell(ReportCellBuilder builder){
        super(builder);
        this.configurations = builder.getButtonConfigs();
    }

    public List<ButtonConfig> getConfigurations(){
        return configurations;
    }
}
