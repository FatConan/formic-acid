package de.themonstrouscavalca.formicacid.templates;

import play.twirl.api.Html;

public class ButtonConfig{
    private final String formName;
    private final String name;
    private final String title;
    private final String label;
    private final String id;
    private final boolean spanWrapper;

    private final String buttonClasses;
    private final Html buttonAttributes;

    public ButtonConfig(String id, String name, String label, String formName, String title, boolean spanWrapper,
                          String buttonClasses, Html buttonAttributes){
        this.id = id;
        this.name = name;
        this.formName = formName;
        this.title = title;
        this.label = label;
        this.spanWrapper = spanWrapper;

        this.buttonClasses = buttonClasses;
        this.buttonAttributes = buttonAttributes;
    }

    public String getFormName(){
        return formName;
    }

    public String getName(){
        return name;
    }

    public String getTitle(){
        return title;
    }

    public String getLabel(){
        return label;
    }

    public String getId(){
        return id;
    }

    public boolean isSpanWrapped(){
        return this.spanWrapper;
    }

    public String getClasses(){
        return buttonClasses;
    }

    public Html getAttributes(){
        return buttonAttributes;
    }
}
