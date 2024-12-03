package de.themonstrouscavalca.formicacid.templates;

import java.util.*;

public class LinkConfigBuilder{
    String formName = "";
    String name = "";
    String label = "";
    String title = "";
    String id = "";
    String href = "";
    boolean wrapInSpan = false;

    List<String> buttonClasses;
    Map<String, String> buttonAttributes;

    public static LinkConfigBuilder instance(){
        return new LinkConfigBuilder();
    }

    public static LinkConfigBuilder of(String name, String label){
        return of(name, label, "");
    }

    public static LinkConfigBuilder of(String name, String label, String formName){
        LinkConfigBuilder builder = new LinkConfigBuilder();
        builder.setName(name);
        builder.setLabel(label);
        builder.setFormName(formName);
        builder.setId(FieldsetNamer.id(name, formName));
        return builder;
    }

    public LinkConfigBuilder(){
        this.buttonClasses = new ArrayList<>();
        this.buttonAttributes = new LinkedHashMap<>();
    }

    public LinkConfigBuilder setLabel(String label){
        this.label = label;
        return this;
    }

    public LinkConfigBuilder spanWrap(){
        this.wrapInSpan = true;
        return this;
    }

    public LinkConfigBuilder setName(String name){
        this.name = name;
        return this;
    }

    public LinkConfigBuilder setId(String id){
        this.id = id;
        return this;
    }

    public LinkConfigBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    public LinkConfigBuilder setHref(String href){
        this.href = href;
        return this;
    }

    public LinkConfigBuilder setFormName(String formName){
        this.formName = formName;
        return this;
    }

    public LinkConfigBuilder addClass(String classString){
        this.buttonClasses.add(classString);
        return this;
    }

    public LinkConfigBuilder disable(){
        return disabled(true);
    }

    public LinkConfigBuilder disabled(boolean disable){
        if(disable){
            this.addAttribute("disabled", "disabled");
            this.addClass("disabled");
        }
        return this;
    }

    public LinkConfigBuilder addAttribute(String key, String value){
        this.buttonAttributes.put(key, value);
        return this;
    }

    public LinkConfigBuilder addData(String dataKey, String value){
        return this.addAttribute(String.format("data-%s", dataKey), value);
    }

    String collateClasses(){
        return String.join(" ", this.buttonClasses);
    }

    public LinkConfig build(){
        return new LinkConfig(this);
    }
}
