package de.themonstrouscavalca.formicacid.templates;

import java.util.List;

public class OptionsConfig{
    private final List<OptionConfig> optionsConfig;

    OptionsConfig(OptionsConfigBuilder builder){
        this.optionsConfig = builder.optionsConfig;
    }

    public List<OptionConfig> getOptionsConfig(){
        return optionsConfig;
    }
}
