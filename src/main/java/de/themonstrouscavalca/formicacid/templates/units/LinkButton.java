package de.themonstrouscavalca.formicacid.templates.units;

import de.themonstrouscavalca.formicacid.templates.ButtonConfig;
import de.themonstrouscavalca.formicacid.templates.LinkConfig;
import de.themonstrouscavalca.formicacid.templates.units.base.IAmAUnit;
import play.twirl.api.Html;

public class LinkButton implements IAmAUnit{
    public static Builder builder(){
        return new Builder();
    }

    private final LinkConfig config;

    public LinkButton(Builder builder){
        this.config = builder.config;
    }

    @Override
    public Html render(){
        return de.themonstrouscavalca.formicacid.twirl.forms.snippets.html.linkButton.render(this.config);
    }

    @Override
    public String name(){
        return this.config != null ? this.config.getName() : " ";
    }

    public static class Builder{
        private LinkConfig config;

        public Builder config(LinkConfig config){
            this.config = config;
            return this;
        }


        public LinkButton build(){
            return new LinkButton(this);
        }
    }
}
