package de.themonstrouscavalca.formicacid.templates.units;

import de.themonstrouscavalca.formicacid.templates.InputConfig;
import de.themonstrouscavalca.formicacid.twirl.forms.extensible.html.checkboxInput;
import de.themonstrouscavalca.formicacid.twirl.forms.extensible.html.numberInput;
import de.themonstrouscavalca.formicacid.twirl.forms.extensible.html.passwordInput;
import de.themonstrouscavalca.formicacid.twirl.forms.extensible.html.textArea;
import de.themonstrouscavalca.formicacid.twirl.forms.extensible.html.textInput;
import de.themonstrouscavalca.formicacid.twirl.forms.html.*;
import de.themonstrouscavalca.formicacid.twirl.forms.html.select;
import de.themonstrouscavalca.formicacid.twirl.forms.snippets.html.submitInput;
import play.twirl.api.Html;

public enum InputType{
    HIDDEN((ic, h) -> hiddenInput.render(ic)),
    TEXT(textInput::render),
    NUMBER(numberInput::render),
    DATE((ic, h) -> dateInput.render(ic)),
    TIME((ic, h) -> timeInput.render(ic)),
    TEXTAREA(textArea::render),
    SELECT((ic, h) -> select.render(ic)),
    SELECT_WITH_OPTS(selectWithOptions::render),
    RADIO((ic, h) -> radioInput.render(ic)),
    CHECKBOX(checkboxInput::render),
    PASSWORD(passwordInput::render),
    URL((ic, h) -> urlInput.render(ic)),
    FILE(fileInput::render),
    SUBMIT((ic, h) -> submitInput.render(ic));

    private final InputRenderer renderer;

    @FunctionalInterface
    interface InputRenderer{
        Html render(InputConfig config, Html additional);
    }

    InputType(InputRenderer renderer){
        this.renderer = renderer;
    }

    public Html render(InputConfig config, Html additional){
        return renderer.render(config, additional);
    }
}
