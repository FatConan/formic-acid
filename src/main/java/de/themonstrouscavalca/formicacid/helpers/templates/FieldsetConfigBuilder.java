package de.themonstrouscavalca.formicacid.helpers.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldsetConfigBuilder{
    private String formName = "";
    private String name = "";
    private String legend = "";
    private String id = "";

    private List<String> fieldsetClasses;
    private Map<String, String> fieldsetAttributes;

    public static FieldsetConfigBuilder instance(){
        return new FieldsetConfigBuilder();
    }

    public static FieldsetConfigBuilder of(String name, String legend){
        return of(name, legend, "");
    }

    public static FieldsetConfigBuilder of(String name, String legend, String formName){
        FieldsetConfigBuilder builder = new FieldsetConfigBuilder();
        builder.setName(name);
        builder.setLegend(legend);
        builder.setFormName(formName);
        builder.setId(FieldsetNamer.id(name, formName));
        return builder;
    }

    public FieldsetConfigBuilder(){
        this.fieldsetClasses = new ArrayList<>();
        this.fieldsetAttributes = new HashMap<>();
    }

    public FieldsetConfigBuilder setLegend(String legend){
        this.legend = legend;
        return this;
    }

    public FieldsetConfigBuilder setName(String name){
        this.name = name;
        return this;
    }

    public FieldsetConfigBuilder setId(String id){
        this.id = id;
        return this;
    }

    public FieldsetConfigBuilder setFormName(String formName){
        this.formName = formName;
        return this;
    }

    public FieldsetConfigBuilder addClass(String classString){
        this.fieldsetClasses.add(classString);
        return this;
    }

    public FieldsetConfigBuilder addAttribute(String key, String value){
        this.fieldsetAttributes.put(key, value);
        return this;
    }

    public FieldsetConfig build(){
        String cssClasses = String.join(" ", this.fieldsetClasses);

        StringBuilder attrBuilder = new StringBuilder();
        this.fieldsetAttributes.entrySet().stream().map(entry -> attrBuilder.append(String.format(" %s=\"%s\" ", entry.getKey(), entry.getValue())));
        String attrs = attrBuilder.toString();

        return new FieldsetConfig(this.id, this.name, this.formName, this.legend, cssClasses, attrs);
    }
}
