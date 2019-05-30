package de.themonstrouscavalca.formicacid.tests.templates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import play.twirl.api.Html;
import de.themonstrouscavalca.formicacid.html.tests.html.FormConstruction;

public class FormConstructionTest{
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void TestFormConstruction(){
        ObjectNode data = mapper.createObjectNode();
        data.put("Test Key", "test value");
        Html result = FormConstruction.render(data);
        System.out.println(result);
    }
}
