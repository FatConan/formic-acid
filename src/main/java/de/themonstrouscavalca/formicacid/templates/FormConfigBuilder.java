package de.themonstrouscavalca.formicacid.templates;

import java.util.*;

import play.twirl.api.Html;

import de.themonstrouscavalca.formicacid.twirl.forms.attributes.html.attributesHtml;

public class FormConfigBuilder{
    private String id = "";
    private String name = "";
    private List<String> classes;
    private Map<String, String> attributes;
    private boolean requiredNotice = false;
    private boolean globalErrors = false;
    private boolean hiddenSubmit = false;

    private List<String> fieldsetClasses;
    private Map<String, String> fieldsetAttributes;

    public static FormConfigBuilder instance(){
        return new FormConfigBuilder();
    }

    public static FormConfigBuilder of(String name){
        return of(name, true, true, true);
    }

    public static FormConfigBuilder of(String name, boolean showRequiredNotice, boolean addGlobalErrors, boolean addHiddenSubmit){
        FormConfigBuilder builder = new FormConfigBuilder();
        builder.setName(name);
        builder.requiredNotice = showRequiredNotice;
        builder.globalErrors = addGlobalErrors;
        builder.hiddenSubmit = addHiddenSubmit;
        builder.setId(FormNamer.id(name));
        return builder;
    }

    public FormConfigBuilder(){
        this.classes = new ArrayList<>();
        this.attributes = new LinkedHashMap<>();
    }

    public FormConfigBuilder setName(String name){
        this.name = name;
        return this;
    }

    public FormConfigBuilder setId(String id){
        this.id = id;
        return this;
    }

    private FormConfigBuilder setRequiredNotice(boolean showRequiredNotice){
        this.requiredNotice = showRequiredNotice;
        return this;
    }

    private FormConfigBuilder setGlobalErrors(boolean globalErrors){
        this.globalErrors = globalErrors;
        return this;
    }

    public FormConfigBuilder setAction(String action){
        this.addAttribute("action", action);
        return this;
    }

    public FormConfigBuilder setMethod(String method){
        this.addAttribute("method", method);
        return this;
    }

    public FormConfigBuilder showRequiredNotice(){
        this.setRequiredNotice(true);
        return this;
    }

    public FormConfigBuilder addGlobalErrors(){
        this.setGlobalErrors(true);
        return this;
    }

    public FormConfigBuilder addClass(String classString){
        this.classes.add(classString);
        return this;
    }

    public FormConfigBuilder addAttribute(String key, String value){
        this.attributes.put(key, value);
        return this;
    }

    public FormConfigBuilder addData(String dataKey, String value){
        return this.addAttribute(String.format("data-%s", dataKey), value);
    }

    public FormConfig build(){
        String cssClasses = String.join(" ", this.classes);
        Html attrs = attributesHtml.render(this.attributes);

        return new FormConfig(this.id, this.name, cssClasses, attrs, this.requiredNotice, this.globalErrors, this.hiddenSubmit);
    }

}
