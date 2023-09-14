package de.themonstrouscavalca.formicacid.templates;

import de.themonstrouscavalca.formicacid.twirl.forms.attributes.html.attributesHtml;
import play.twirl.api.Html;

import java.util.List;

public class InputConfig{
    private final String formName;
    private final String name;
    private final String label;
    private final String id;
    private final boolean required;
    private final boolean withLabel;
    private final String value;

    private final String placeholder;

    private final String wrapperClasses;
    private final String inputClasses;
    private final Html wrapperAttributes;
    private final Html inputAttributes;

    private final List<InputValuePair> inputValuesPairs;

    InputConfig(InputConfigBuilder builder){
        String inputClasses = builder.collateInputClasses();
        String wrapperClasses = builder.collateWrapperClasses();
        String id = builder.explicitOrGeneratedId();

        Html inputAttrs = attributesHtml.render(builder.inputAttributes);
        Html wrapperAttrs = attributesHtml.render(builder.wrapperAttributes);

        this.id = id;
        this.name = builder.name;
        this.required = builder.required;
        this.formName = builder.formName;
        this.label = builder.label;
        this.withLabel = builder.label != null && !builder.label.isEmpty();
        this.value = builder.value;

        this.placeholder = builder.placeholder;
        this.inputValuesPairs = builder.inputValuePairs;

        this.wrapperClasses = wrapperClasses;
        this.inputClasses = inputClasses;
        this.wrapperAttributes = wrapperAttrs;
        this.inputAttributes = inputAttrs;
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

    public boolean isWithLabel(){
        return withLabel;
    }

    public String getWrapperClasses(){
        return wrapperClasses;
    }

    public String getInputClasses(){
        return inputClasses;
    }

    public Html getWrapperAttributes(){
        return wrapperAttributes;
    }

    public Html getInputAttributes(){
        return inputAttributes;
    }

    public List<InputValuePair> getInputValuesPairs(){
        return inputValuesPairs;
    }
}
