package de.themonstrouscavalca.formicacid.templates;

import java.util.ArrayList;
import java.util.List;

public class OptionConfigBuilder{
    String label = "";
    String value = "";
    List<String> optionClasses = new ArrayList<>();

    public static OptionConfigBuilder instance(){
        return new OptionConfigBuilder();
    }

    public static OptionConfigBuilder of(String label, String value){
        OptionConfigBuilder builder = new OptionConfigBuilder();
        builder.setLabel(label);
        builder.setValue(value);
        return builder;
    }

    public OptionConfigBuilder setLabel(String label){
        this.label = label;
        return this;
    }

    public OptionConfigBuilder setValue(String value){
        this.value = value;
        return this;
    }

    public OptionConfigBuilder addClass(String classString){
        this.optionClasses.add(classString);
        return this;
    }

    String collateClasses(){
        return String.join(" ", this.optionClasses);
    }

    public OptionConfig build(){
        return new OptionConfig(this);
    }
}
