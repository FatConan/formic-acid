package de.themonstrouscavalca.formicacid.tests.templates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.templates.FormConfig;
import de.themonstrouscavalca.formicacid.templates.InputConfig;
import de.themonstrouscavalca.formicacid.templates.InputConfigBuilder;
import de.themonstrouscavalca.formicacid.templates.units.Form;
import de.themonstrouscavalca.formicacid.templates.units.Input;
import de.themonstrouscavalca.formicacid.templates.units.InputType;
import de.themonstrouscavalca.formicacid.util.Pair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.twirl.api.Html;

import java.util.*;


public class FormVersionTwoConstructionTest{
    private static final Logger logger = LoggerFactory.getLogger(FormVersionTwoConstructionTest.class);

    @Test
    public void TestFormConstruction(){
        Form formV2 = Form.builder().config(
                        FormConfig.builder().setName("form").build())
                .unit("input_one", Input.builder().config(
                        InputConfig.builder().setName("input_one")
                ).type(InputType.TEXT).build())
                .build();

        Html testOne = formV2.render();
        logger.error(testOne.toString());

        Map<String, String[]> data = new HashMap<>();
        data.put("input_one", new String[]{"input_one-test-data"});
        formV2.handleData(data);
        Html testTwo = formV2.render();
        logger.error(testTwo.toString());

        final ObjectMapper mapper = new ObjectMapper();
        ObjectNode dataNode = mapper.createObjectNode();
        dataNode.put("input_one", "This is some data");
        formV2.handleData(dataNode);
        Html testThree = formV2.render();
        logger.error(testThree.toString());
    }

    @Test
    public void TestFormChecklistConstruction(){
        List<Pair<String, String>> options = new ArrayList<>();
        for(String value: new String[]{"1", "2", "3", "4", "5", "A", "B", "C", "D", "E"}){
            options.add(new Pair<>(value, value));
        }

        Map<String, String[]> idMap = new HashMap<>();
        idMap.put("selected", new String[]{"1", "5", "B", "D"});

        Form formV2Checkboxes = Form.builder()
                .config(FormConfig.builder().setName("form").build())
                .unit("selected", Input.builder().config(
                        InputConfigBuilder.of("selected", "Select", "form")
                                .addValueOptions(options)
                ).type(InputType.CHECKBOX).build())
                .build();

        formV2Checkboxes.handleData(idMap);
        Html test = formV2Checkboxes.render();
        logger.error(test.toString());
    }
}
