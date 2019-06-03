package de.themonstrouscavalca.formicacid.tests.templates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.templates.FormConfigBuilder;
import de.themonstrouscavalca.formicacid.templates.Helpers;
import org.junit.Test;
import play.twirl.api.Html;

import static org.junit.Assert.assertEquals;


public class FormConstructionTest{
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void TestFormConstruction(){
        final String formName = "form";
        final String emptyFormReference = "<form id=\"form_form\" class=\"form \"></form>";
        final String standardFormReference = "<form id=\"form_form\" class=\"form \"><div class=\"global-errors\"></div>" +
                "<div class=\"input-row required\"><p class=\"indicates-required\">Indicates a required field.</p></div>" +
                "<input type=\"submit\" class=\"hidden\" /></form>";
        final String actionAndMethodFormReference = "<form id=\"form_form\" class=\"form \" action=\"/form-submission-url\" method=\"POST\"><div class=\"global-errors\"></div>" +
                "<div class=\"input-row required\"><p class=\"indicates-required\">Indicates a required field.</p></div>" +
                "<input type=\"submit\" class=\"hidden\" /></form>";
        final String elementedFormReferenceWithData = "<form id=\"form_test\" class=\"form \" data-data=\"{&quot;Test Key&quot;:&quot;test value&quot;}\">" +
                "<div class=\"global-errors\"></div>" +
                "<p>A form is rendered without inputs</p>" +
                "<div class=\"input-row required\"><p class=\"indicates-required\">Indicates a required field.</p></div>" +
                "<input type=\"submit\" class=\"hidden\" /></form>";

        Html form = Helpers.reduce(de.themonstrouscavalca.formicacid.twirl.forms.html.form.render(
                FormConfigBuilder.of(formName, false, false, false).build(), null));
        assertEquals("Rendered empty form HTML doesn't match expectation", emptyFormReference, form.body());

        form = Helpers.reduce(de.themonstrouscavalca.formicacid.twirl.forms.html.form.render(
                FormConfigBuilder.of(formName).build(), null));
        assertEquals("Rendered standard form HTML doesn't match expectation", standardFormReference, form.body());

        form = Helpers.reduce(de.themonstrouscavalca.formicacid.twirl.forms.html.form.render(
                FormConfigBuilder.of(formName).setAction("/form-submission-url").setMethod("POST").build(), null));
        assertEquals("Rendered action and method form HTML doesn't match expectation", actionAndMethodFormReference, form.body());

        ObjectNode data = mapper.createObjectNode();
        data.put("Test Key", "test value");
        form = Helpers.reduce(de.themonstrouscavalca.formicacid.twirl.tests.html.FormConstruction.render(data));
        assertEquals("Rendered elemented form with data form HTML doesn't match expectation", elementedFormReferenceWithData, form.body());
    }
}
