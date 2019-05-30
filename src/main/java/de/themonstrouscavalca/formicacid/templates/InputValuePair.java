package de.themonstrouscavalca.formicacid.templates;

public class InputValuePair{
    private final String label;
    private final String value;

    public InputValuePair(String value, String label){
        this.label = label;
        this.value = value;
    }

    public String getLabel(){
        return label;
    }

    public String getValue(){
        return value;
    }
}
