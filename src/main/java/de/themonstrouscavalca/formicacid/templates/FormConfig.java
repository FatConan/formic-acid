package de.themonstrouscavalca.formicacid.templates;

import play.twirl.api.Html;

public class FormConfig{
    private final String id;
    private final String name;
    private final String classes;
    private final Html attributes;
    private final boolean showRequiredNotice;
    private final boolean addGlobalErrors;

    public FormConfig(String id, String name, String classes, Html attributes, Boolean showRequiredNotice, Boolean addGlobalErrors){
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.attributes = attributes;
        this.showRequiredNotice = showRequiredNotice;
        this.addGlobalErrors = addGlobalErrors;
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
}
