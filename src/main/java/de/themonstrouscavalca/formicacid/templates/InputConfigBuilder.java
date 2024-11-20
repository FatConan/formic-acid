package de.themonstrouscavalca.formicacid.templates;
import de.themonstrouscavalca.formicacid.util.Pair;
import java.util.*;
import java.util.stream.Collectors;

import de.themonstrouscavalca.formicacid.twirl.forms.attributes.html.attributesHtml;
import play.twirl.api.Html;


public class InputConfigBuilder{
    String formName = "";
    String name = "";
    String label = "";
    String explicitId = null;
    String value = null;
    boolean required = false;

    String placeholder = "";

    List<String> wrapperClasses = new ArrayList<>();
    List<String> inputClasses = new ArrayList<>();;
    List<String> errors = new ArrayList<>();
    String data;
    List<String> dataArray;

    Map<String, String> wrapperAttributes = new LinkedHashMap<>();
    Map<String, String> inputAttributes = new LinkedHashMap<>();
    List<InputValuePair> inputValuePairs = new ArrayList<>();

    public static InputConfigBuilder instance(){
        return new InputConfigBuilder();
    }

    public static InputConfigBuilder of(String name){
        return of(name, "", "");
    }

    public static InputConfigBuilder of(String name, String label){
        return of(name, label, "");
    }

    public static InputConfigBuilder of(String name, String label, String formName){
        InputConfigBuilder builder = new InputConfigBuilder();
        builder.setName(name);
        builder.setLabel(label);
        builder.setFormName(formName);
        return builder;
    }

    public InputConfigBuilder addError(String error){
        this.errors.add(error);
        return this;
    }

    public InputConfigBuilder addErrors(List<String> errors){
        this.errors.addAll(errors);
        return this;
    }

    public InputConfigBuilder setData(String data){
        this.data = data;
        return this;
    }

    public InputConfigBuilder setData(String[] data){
        this.dataArray = List.of(data);
        return this;
    }

    public InputConfigBuilder setPlaceholder(String placeholder){
        this.placeholder = placeholder;
        return this;
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
        this.explicitId = id;
        return this;
    }

    public InputConfigBuilder setFormName(String formName){
        this.formName = formName;
        return this;
    }

    public InputConfigBuilder addValueOption(String value, String label){
        this.inputValuePairs.add(new InputValuePair(value, label));
        return this;
    }

    public InputConfigBuilder addValueOptions(List<Pair<String, String>> pairs){
        this.inputValuePairs.addAll(pairs.stream().map(pair -> new InputValuePair(pair.getKey(), pair.getValue())).collect(Collectors.toList()));
        return this;
    }

    public InputConfigBuilder require(){
        this.required = true;
        return this;
    }

    public InputConfigBuilder disable(){
       return disabled(true);
    }

    public InputConfigBuilder disabled(boolean disable){
        if(disable){
            this.addInputAttribute("disabled", "disabled");
            this.addWrapperClass("disabled");
        }
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

    public InputConfigBuilder addWrapperAttribute(String key, String value){
        this.wrapperAttributes.put(key, value);
        return this;
    }

    public String valueOrData(){
        if(this.value != null){
            return this.value;
        }else if(this.data != null){
            return this.data;
        }else if(this.dataArray != null && !this.dataArray.isEmpty()){
            return this.dataArray.get(0);
        }
        return null;
    }

    public Map<String, String> collectInputAttributes(){
        Map<String, String> inputAttributes = new LinkedHashMap<>(this.inputAttributes);

        if(this.value != null && (
                (this.data != null && this.data.equals(this.value)) || (this.dataArray != null && this.dataArray.contains(this.value)))){
            inputAttributes.put("checked", null);
        }
        return inputAttributes;
    }

    public InputConfigBuilder addWrapperData(String dataKey, String value){
        return this.addWrapperAttribute(String.format("data-%s", dataKey), value);
    }

    public InputConfigBuilder addInputAttribute(String key, String value){
        this.inputAttributes.put(key, value);
        return this;
    }

    public InputConfigBuilder addInputData(String dataKey, String value){
        return this.addInputAttribute(String.format("data-%s", dataKey), value);
    }

    String collateInputClasses(){
        return String.join(" ", this.inputClasses);
    }

    String collateWrapperClasses(){
        return String.join(" ", this.wrapperClasses);
    }

    String explicitOrGeneratedId(){
        return this.explicitId != null ? this.explicitId : InputNamer.id(this.name, this.formName);
    }

    public InputConfig build(){
        return new InputConfig(this);
    }
}
