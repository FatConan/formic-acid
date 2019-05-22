package de.themonstrouscavalca.formicacid.helpers.templates;

import java.util.List;

public class InputConfig{
    private final String formName;
    private final String name;
    private final String label;
    private final String id;
    private final boolean required;
    private final String value;

    private String placeholder;

    private final String wrapperClasses;
    private final String inputClasses;
    private final String wrapperAttributes;
    private final String inputAttributes;

    private final List<InputValuePair> inputValuesPairs;

    public InputConfig(String id, String name, String formName, String label, Boolean required,
                       String placeholder,
                       String wrapperClasses, String inputClasses, String wrapperAttributes, String inputAttributes,
                       List<InputValuePair> inputValuePairs,
                       String value){
        this.id = id;
        this.name = name;
        this.required = required;
        this.formName = formName;
        this.label = label;
        this.value = value;

        this.placeholder = placeholder;
        this.inputValuesPairs = inputValuePairs;

        this.wrapperClasses = wrapperClasses;
        this.inputClasses = inputClasses;
        this.wrapperAttributes = wrapperAttributes;
        this.inputAttributes = inputAttributes;
    }

    public String getFormName(){
        return formName;
    }

    public String getName(){
        return name;
    }

    public String getLabel(){
        return label;
    }

    public String getValue(){
        return value;
    }

    public String getPlaceholder(){
        return placeholder;
    }

    public String getId(){
        return id;
    }

    public boolean isRequired(){
        return required;
    }

    public String getWrapperClasses(){
        return wrapperClasses;
    }

    public String getInputClasses(){
        return inputClasses;
    }

    public String getWrapperAttributes(){
        return wrapperAttributes;
    }

    public String getInputAttributes(){
        return inputAttributes;
    }

    public List<InputValuePair> getInputValuesPairs(){
        return inputValuesPairs;
    }
}
