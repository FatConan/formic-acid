package de.themonstrouscavalca.formicacid.helpers.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputConfigBuilder{
    private String formName = "";
    private String name = "";
    private String label = "";
    private String id = "";
    private String value = null;
    private boolean required = false;

    private List<String> wrapperClasses;
    private List<String> inputClasses;
    private Map<String, String> wrapperAttributes;
    private Map<String, String> inputAttributes;

    public static InputConfigBuilder instance(){
        return new InputConfigBuilder();
    }

    public static InputConfigBuilder of(String name, String label){
        return of(name, label, "");
    }

    public static InputConfigBuilder of(String name, String label, String formName){
        InputConfigBuilder builder = new InputConfigBuilder();
        builder.setName(name);
        builder.setLabel(label);
        builder.setFormName(formName);
        builder.setId(InputNamer.id(name, formName));
        return builder;
    }

    public InputConfigBuilder(){
        this.wrapperClasses = new ArrayList<>();
        this.inputClasses = new ArrayList<>();
        this.wrapperAttributes = new HashMap<>();
        this.inputAttributes = new HashMap<>();
    }

    public InputConfigBuilder setLabel(String label){
        this.label = label;
        return this;
    }

    public InputConfigBuilder setName(String name){
        this.name = name;
        return this;
    }

    public InputConfigBuilder setId(String id){
        this.id = id;
        return this;
    }

    public InputConfigBuilder setFormName(String formName){
        this.formName = formName;
        return this;
    }

    public InputConfigBuilder require(){
        this.required = true;
        return this;
    }

    public InputConfigBuilder disable(){
        this.addInputAttribute("disabled", "disabled");
        this.addWrapperClass("disabled");
        return this;
    }

    public InputConfigBuilder setValue(String value){
        this.value = value;
        return this;
    }

    public InputConfigBuilder addInputClass(String classString){
        this.inputClasses.add(classString);
        return this;
    }

    public InputConfigBuilder addWrapperClass(String classString){
        this.wrapperClasses.add(classString);
        return this;
    }

    public InputConfigBuilder addWrapperAttributes(String key, String value){
        this.wrapperAttributes.put(key, value);
        return this;
    }

    public InputConfigBuilder addInputAttribute(String key, String value){
        this.inputAttributes.put(key, value);
        return this;
    }

    public InputConfig build(){
        String inputClasses = String.join(" ", this.inputClasses);
        String wrapperClasses = String.join(" ", this.wrapperClasses);

        StringBuilder inputAttrBuilder = new StringBuilder();
        this.inputAttributes.entrySet().stream().map(entry -> inputAttrBuilder.append(String.format(" %s=\"%s\" ", entry.getKey(), entry.getValue())));
        String inputAttrs = inputAttrBuilder.toString();

        StringBuilder wrapperAttrBuilder = new StringBuilder();
        this.wrapperAttributes.entrySet().stream().map(entry -> wrapperAttrBuilder.append(String.format(" %s=\"%s\" ", entry.getKey(), entry.getValue())));
        String wrapperAttrs = wrapperAttrBuilder.toString();

        return new InputConfig(this.id, this.name, this.formName, this.label, this.required,
                wrapperClasses, inputClasses, wrapperAttrs, inputAttrs,
                this.value);
    }
}
