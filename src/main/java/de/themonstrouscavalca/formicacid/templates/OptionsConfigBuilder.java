package de.themonstrouscavalca.formicacid.templates;

import de.themonstrouscavalca.formicacid.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class OptionsConfigBuilder{
    final List<OptionConfig> optionsConfig = new ArrayList<>();

    public static OptionsConfigBuilder instance(){
        return new OptionsConfigBuilder();
    }

    public OptionsConfigBuilder addOption(String label, String value){
        this.optionsConfig.add(OptionConfigBuilder.of(label, value).build());
        return this;
    }

    public OptionsConfigBuilder addOptions(List<Pair<String, String>> options){
        for(Pair<String, String> option : options){
            this.optionsConfig.add(OptionConfigBuilder.of(option.getKey(), option.getValue()).build());
        }
        return this;
    }

    public OptionsConfig build(){
        return new OptionsConfig(this);
    }
}
