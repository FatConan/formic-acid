package de.themonstrouscavalca.formicacid.tests.templates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.templates.FormConfig;
import de.themonstrouscavalca.formicacid.templates.InputConfig;
import de.themonstrouscavalca.formicacid.templates.Helpers;
import de.themonstrouscavalca.formicacid.templates.units.Form;
import de.themonstrouscavalca.formicacid.templates.units.Input;
import de.themonstrouscavalca.formicacid.templates.units.InputType;
import org.junit.Test;
import play.twirl.api.Html;

import java.util.HashMap;
import java.util.Map;


public class FormVersionTwoContructionTest{
    @Test
    public void TestFormConstruction(){
        Form formV2 = Form.builder().config(
                        FormConfig.builder().setName("form").build())
                .unit("input_one", Input.builder().config(
                        InputConfig.builder().setName("input_one")
                ).type(InputType.TEXT).build())
                .build();

        Html testOne = formV2.render();

        Map<String, String[]> data = new HashMap<>();
        data.put("input_one", new String[]{"input_one-test-data"});
        formV2.data(data);
        Html testTwo = formV2.render();

        final ObjectMapper mapper = new ObjectMapper();
        ObjectNode dataNode = mapper.createObjectNode();
        dataNode.put("input_one", "This is some data");
        formV2.data(dataNode);
        Html testThree = formV2.render();

    }
}
