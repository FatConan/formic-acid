package de.themonstrouscavalca.formicacid.templates;

import de.themonstrouscavalca.formicacid.twirl.forms.attributes.html.attributesHtml;
import play.twirl.api.Html;

public class FormConfig{
    private final String id;
    private final String name;
    private final String classes;
    private final Html attributes;

    private final boolean showRequiredNotice;
    private final boolean addGlobalErrors;
    private final boolean addHiddenSubmit;

    FormConfig(FormConfigBuilder builder){

        Html attrs = attributesHtml.render(builder.attributes);

        this.id = builder.id;
        this.name = builder.name;
        this.classes = builder.collateClasses();
        this.attributes = attrs;
        this.showRequiredNotice = builder.requiredNotice;
        this.addGlobalErrors = builder.globalErrors;
        this.addHiddenSubmit = builder.hiddenSubmit;
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
