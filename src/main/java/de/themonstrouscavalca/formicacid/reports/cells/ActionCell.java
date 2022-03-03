package de.themonstrouscavalca.formicacid.reports.cells;

import de.themonstrouscavalca.formicacid.templates.ButtonConfig;

import java.util.List;

public class ActionCell extends AbstractCell{
    private final List<ButtonConfig> configurations;

    ActionCell(List<ButtonConfig> configurations, int colSpan){
        super(configurations, colSpan);
        this.configurations = configurations;
    }

    public List<ButtonConfig> getConfigurations(){
        return configurations;
    }
}
