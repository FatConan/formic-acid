package de.themonstrouscavalca.formicacid.templates.units;

import de.themonstrouscavalca.formicacid.templates.ButtonConfig;
import de.themonstrouscavalca.formicacid.templates.units.base.IAmAUnit;
import play.twirl.api.Html;

public class Button implements IAmAUnit{
    public static Builder builder(){
        return new Builder();
    }

    private final ButtonConfig config;

    public Button(Builder builder){
        this.config = builder.config;
    }

    @Override
    public Html render(){
        return de.themonstrouscavalca.formicacid.twirl.forms.snippets.html.button.render(this.config);
    }

    @Override
    public String name(){
        return this.config != null ? this.config.getName() : " ";
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
