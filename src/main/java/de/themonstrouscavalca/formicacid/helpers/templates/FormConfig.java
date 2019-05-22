package de.themonstrouscavalca.formicacid.helpers.templates;

public class FormConfig{
    private final String id;
    private final String name;
    private final String classes;
    private final String attributes;
    private final boolean showRequiredNotice;
    private final boolean addGlobalErrors;

    public FormConfig(String id, String name, String classes, String attributes, Boolean showRequiredNotice, Boolean addGlobalErrors){
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

    public String getAttributes(){
        return attributes;
    }

    public boolean getShowRequiredNotice(){
        return showRequiredNotice;
    }

    public boolean getAddGlobalErrors(){
        return addGlobalErrors;
    }
}
