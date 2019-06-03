package de.themonstrouscavalca.formicacid.templates;

import play.twirl.api.Html;

public class FormConfig{
    private final String id;
    private final String name;
    private final String classes;
    private final Html attributes;

    private final boolean showRequiredNotice;
    private final boolean addGlobalErrors;
    private final boolean addHiddenSubmit;

    public FormConfig(String id, String name, String classes, Html attributes, boolean showRequiredNotice, boolean addGlobalErrors,
            boolean addHiddenSubmit){
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.attributes = attributes;
        this.showRequiredNotice = showRequiredNotice;
        this.addGlobalErrors = addGlobalErrors;
        this.addHiddenSubmit = addHiddenSubmit;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return name;
    }

    public String getClasses(){
        return classes;
    }

    public Html getAttributes(){
        return attributes;
    }

    public boolean getShowRequiredNotice(){
        return showRequiredNotice;
    }

    public boolean getAddGlobalErrors(){
        return addGlobalErrors;
    }

    public boolean getAddHiddenSubmit(){
        return addHiddenSubmit;
    }
}
