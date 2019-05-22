package de.themonstrouscavalca.formicacid.helpers.templates;

public class FieldsetConfig{
    private final String formName;
    private final String name;
    private final String legend;
    private final String id;

    private final String fieldsetClasses;
    private final String fieldsetAttributes;

    public FieldsetConfig(String id, String name, String formName, String legend,
                          String fieldsetClasses, String fieldsetAttributes){
        this.id = id;
        this.name = name;
        this.formName = formName;
        this.legend = legend;

        this.fieldsetClasses = fieldsetClasses;
        this.fieldsetAttributes = fieldsetAttributes;
    }

    public String getFormName(){
        return formName;
    }

    public String getName(){
        return name;
    }

    public String getLegend(){
        return legend;
    }

    public String getId(){
        return id;
    }

    public String getClasses(){
        return fieldsetClasses;
    }

    public String getAttributes(){
        return fieldsetAttributes;
    }
}
