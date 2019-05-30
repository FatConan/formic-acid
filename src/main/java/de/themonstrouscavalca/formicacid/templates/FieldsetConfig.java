package de.themonstrouscavalca.formicacid.templates;

import play.twirl.api.Html;

public class FieldsetConfig{
    private final String formName;
    private final String name;
    private final String legend;
    private final String id;

    private final String fieldsetClasses;
    private final Html fieldsetAttributes;

    public FieldsetConfig(String id, String name, String formName, String legend,
                          String fieldsetClasses, Html fieldsetAttributes){
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

    public Html getAttributes(){
        return fieldsetAttributes;
    }
}
