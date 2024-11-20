package de.themonstrouscavalca.formicacid.templates.units;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.templates.ButtonConfig;
import de.themonstrouscavalca.formicacid.templates.units.base.IAmAUnit;
import play.twirl.api.Html;

import java.util.Map;


public class Button implements IAmAUnit{
    public static Builder builder(){
        return new Builder();
    }

    private ButtonConfig config;

    public Button(Builder builder){
        this.config = builder.config;
    }

    @Override
    public Html render(){
        return de.themonstrouscavalca.formicacid.twirl.forms.snippets.html.button.render(this.config);
    }

    @Override
    public void data(String data){

    }

    @Override
    public void data(String[] data){

    }

    @Override
    public void handleData(Map<String, String[]> data){

    }

    @Override
    public void handleData(JsonNode data){

    }

    @Override
    public void errors(String errors){

    }

    @Override
    public void handleErrors(JsonNode error){

    }

    public static class Builder{
        private ButtonConfig config;

        public Builder config(ButtonConfig config){
            this.config = config;
            return this;
        }


        public Button build(){
            return new Button(this);
        }
    }
}
