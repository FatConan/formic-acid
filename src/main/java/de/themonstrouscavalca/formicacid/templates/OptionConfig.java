package de.themonstrouscavalca.formicacid.templates;

public class OptionConfig{
    private final String label;
    private final String value;
    private final String optionClasses;

    OptionConfig(OptionConfigBuilder builder){
        this.label = builder.label;
        this.value = builder.value;
        this.optionClasses = builder.collateClasses();
    }

    public String getLabel(){
        return label;
    }

    public String getValue(){
        return value;
    }

    public String getOptionClasses(){
        return optionClasses;
    }
}
