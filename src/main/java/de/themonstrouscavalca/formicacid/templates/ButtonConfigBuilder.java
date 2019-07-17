package de.themonstrouscavalca.formicacid.templates;

import de.themonstrouscavalca.formicacid.twirl.forms.attributes.html.attributesHtml;
import play.twirl.api.Html;

import java.util.*;

public class ButtonConfigBuilder{
    private String formName = "";
    private String name = "";
    private String label = "";
    private String title = "";
    private String id = "";
    private boolean wrapInSpan = false;

    private List<String> buttonClasses;
    private Map<String, String> buttonAttributes;

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

    public ButtonConfig build(){
        String cssClasses = String.join(" ", this.buttonClasses);
        Html attrs = attributesHtml.render(this.buttonAttributes);
        return new ButtonConfig(this.id, this.name, this.label, this.formName, this.title, this.wrapInSpan, cssClasses, attrs);
    }
}
