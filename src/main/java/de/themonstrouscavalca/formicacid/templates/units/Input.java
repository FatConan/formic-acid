package de.themonstrouscavalca.formicacid.templates.units;

import de.themonstrouscavalca.formicacid.templates.InputConfigBuilder;
import de.themonstrouscavalca.formicacid.templates.units.base.IAmAUnit;
import play.twirl.api.Html;
import play.twirl.api.HtmlFormat;
import scala.collection.immutable.Seq;
import scala.jdk.javaapi.CollectionConverters;

import java.util.ArrayList;
import java.util.List;

public class Input implements IAmAUnit{
    public static Builder builder(){
        return new Builder();
    }

    private final InputConfigBuilder configBuilder;
    private final InputType inputType;
    private final List<IAmAUnit> units = new ArrayList<>();

    public Input(Builder builder){
        this.configBuilder = builder.configBuilder;
        this.inputType = builder.inputType;
        this.units.addAll(builder.units);
    }

    @Override
    public void errors(String errors){
        this.configBuilder.errors(errors);
    }

    @Override
    public Html render(){
        List<Html> renders = new ArrayList<>();
        for(IAmAUnit unit : units){
            renders.add(unit.render());
        }
        Seq<Html> htmls = CollectionConverters.asScala(renders).toSeq();
        return this.inputType.render(this.configBuilder.build(),
                HtmlFormat.fill(htmls));
    }

    @Override
    public void data(String data){
        this.configBuilder.setData(data);
    }

    @Override
    public void data(String[] data){
        this.configBuilder.setData(data);
    }

    @Override
    public String name(){
        return this.configBuilder != null ? this.configBuilder.build().getName() : "";
    }

    public static class Builder{
        private InputConfigBuilder configBuilder;
        private InputType inputType = InputType.TEXT;
        private final List<IAmAUnit> units = new ArrayList<>();

        public Builder config(InputConfigBuilder configBuilder){
            this.configBuilder = configBuilder;
            return this;
        }

        public Builder type(InputType inputType){
            this.inputType = inputType;
            return this;
        }

        public Builder unit(IAmAUnit unit){
            this.units.add(unit);
            return this;
        }

        public Input build(){
            return new Input(this);
        }
    }
}
