package de.themonstrouscavalca.formicacid.templates;

import de.themonstrouscavalca.formicacid.twirl.forms.attributes.html.attributesHtml;
import play.twirl.api.Html;

import java.util.*;

public class ButtonConfigBuilder{
    String formName = "";
    String name = "";
    String label = "";
    String title = "";
    String id = "";
    boolean wrapInSpan = false;

    List<String> buttonClasses;
    Map<String, String> buttonAttributes;

    public static ButtonConfigBuilder instance(){
        return new ButtonConfigBuilder();
    }

    public static ButtonConfigBuilder of(String name, String label){
        return of(name, label, "");
    }

    public static ButtonConfigBuilder of(String name, String label, String formName){
        ButtonConfigBuilder builder = new ButtonConfigBuilder();
        builder.setName(name);
        builder.setLabel(label);
        builder.setFormName(formName);
        builder.setId(FieldsetNamer.id(name, formName));
        return builder;
    }

    public ButtonConfigBuilder(){
        this.buttonClasses = new ArrayList<>();
        this.buttonAttributes = new LinkedHashMap<>();
    }

    public ButtonConfigBuilder setLabel(String label){
        this.label = label;
        return this;
    }

    public ButtonConfigBuilder spanWrap(){
        this.wrapInSpan = true;
        return this;
    }

    public ButtonConfigBuilder setName(String name){
        this.name = name;
        return this;
    }

    public ButtonConfigBuilder setId(String id){
        this.id = id;
        return this;
    }

    public ButtonConfigBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    public ButtonConfigBuilder setFormName(String formName){
        this.formName = formName;
        return this;
    }

    public ButtonConfigBuilder addClass(String classString){
        this.buttonClasses.add(classString);
        return this;
    }

    public ButtonConfigBuilder addAttribute(String key, String value){
        this.buttonAttributes.put(key, value);
        return this;
    }

    public ButtonConfigBuilder addData(String dataKey, String value){
        return this.addAttribute(String.format("data-%s", dataKey), value);
    }

    String collateClasses(){
        return String.join(" ", this.buttonClasses);
    }

    public ButtonConfig build(){
        return new ButtonConfig(this);
    }
}
