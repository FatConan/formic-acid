package de.themonstrouscavalca.formicacid.templates;

import de.themonstrouscavalca.formicacid.twirl.forms.attributes.html.attributesHtml;
import play.twirl.api.Html;

public class FieldsetConfig{
    private final String formName;
    private final String name;
    private final String legend;
    private final String id;

    private final String fieldsetClasses;
    private final Html fieldsetAttributes;

    FieldsetConfig(FieldsetConfigBuilder builder){

        Html attrs = attributesHtml.render(builder.fieldsetAttributes);

        this.id = builder.id;
        this.name = builder.name;
        this.formName = builder.formName;
        this.legend = builder.legend;

        this.fieldsetClasses = builder.collateClasses();
        this.fieldsetAttributes = attrs;
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
