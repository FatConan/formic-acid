package de.themonstrouscavalca.formicacid.templates;

import de.themonstrouscavalca.formicacid.twirl.forms.attributes.html.attributesHtml;
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

    ButtonConfig(ButtonConfigBuilder builder){
        Html attrs = attributesHtml.render(builder.buttonAttributes);

        this.id = builder.id;
        this.name = builder.name;
        this.formName = builder.formName;
        this.title = builder.title;
        this.label = builder.label;
        this.spanWrapper = builder.wrapInSpan;

        this.buttonClasses = builder.collateClasses();
        this.buttonAttributes = attrs;
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
