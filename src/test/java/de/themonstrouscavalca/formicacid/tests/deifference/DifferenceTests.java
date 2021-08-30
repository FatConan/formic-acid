package de.themonstrouscavalca.formicacid.tests.deifference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.difference.DifferenceProcessor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static de.themonstrouscavalca.formicacid.difference.DifferenceProcessor.IN;
import static de.themonstrouscavalca.formicacid.difference.DifferenceProcessor.OUT;
import static de.themonstrouscavalca.formicacid.difference.DifferenceProcessor.ALTERED;
import static de.themonstrouscavalca.formicacid.difference.DifferenceProcessor.HIDDEN_VALUE;
import static de.themonstrouscavalca.formicacid.difference.DifferenceProcessor.EMPTY_VALUE;
import static de.themonstrouscavalca.formicacid.difference.DifferenceProcessor.ALTERED_FORMAT;
import static de.themonstrouscavalca.formicacid.difference.DifferenceProcessor.KEYED_VALUE_FORMAT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DifferenceTests{
    private static final Logger logger = LoggerFactory.getLogger(DifferenceTests.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void TestDifferenceKeyAddition() throws JsonProcessingException{
        JsonNode from = mapper.readTree("{\n" +
                "  \"key1\":\"value1\"\n" +
                "}");
        JsonNode to = mapper.readTree("{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key2\":\"value2\"\n" +
                "}");

        JsonNode diff = DifferenceProcessor.differences(from, to);
        assertTrue(diff.has(OUT));
        assertTrue(diff.get(OUT).isEmpty());
        assertTrue(diff.has(ALTERED));
        assertTrue(diff.get(ALTERED).isEmpty());

        assertTrue(diff.has(IN));
        assertTrue(diff.get(IN).has("key2"));
        assertEquals("value2", diff.get(IN).get("key2").asText());
    }

    @Test
    public void TestDifferenceKeyRemoval() throws JsonProcessingException{
        JsonNode from = mapper.readTree("{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key2\":\"value2\"\n" +
                "}");
        JsonNode to = mapper.readTree("{\n" +
                "  \"key1\":\"value1\"\n" +
                "}");


        JsonNode diff = DifferenceProcessor.differences(from, to);
        assertTrue(diff.has(IN));
        assertTrue(diff.get(IN).isEmpty());
        assertTrue(diff.has(ALTERED));
        assertTrue(diff.get(ALTERED).isEmpty());

        assertTrue(diff.has(OUT));
        assertTrue(diff.get(OUT).has("key2"));
        assertEquals("value2", diff.get(OUT).get("key2").asText());
    }

    @Test
    public void TestDifferenceKeyChange() throws JsonProcessingException{
        JsonNode from = mapper.readTree("{\n" +
                "  \"key1\":\"value1\"\n" +
                "}");
        JsonNode to = mapper.readTree("{\n" +
                "  \"key1\":\"value2\"\n" +
                "}");


        JsonNode diff = DifferenceProcessor.differences(from, to);
        assertTrue(diff.has(IN));
        assertTrue(diff.get(IN).isEmpty());
        assertTrue(diff.has(OUT));
        assertTrue(diff.get(OUT).isEmpty());

        assertTrue(diff.has(ALTERED));
        assertTrue(diff.get(ALTERED).has("key1"));
        assertEquals(String.format(ALTERED_FORMAT, "value1", "value2"), diff.get(ALTERED).get("key1").asText());
    }

    @Test
    public void TestDifferenceKeyChangeToArray() throws JsonProcessingException{
        JsonNode from = mapper.readTree("{\n" +
                "  \"key1\":\"value1\"\n" +
                "}");
        JsonNode to = mapper.readTree("{\n" +
                "  \"key1\":[\"value1\", \"value2\"]\n" +
                "}");


        JsonNode diff = DifferenceProcessor.differences(from, to);
        assertTrue(diff.has(IN));
        assertTrue(diff.get(IN).isEmpty());
        assertTrue(diff.has(OUT));
        assertTrue(diff.get(OUT).isEmpty());

        assertTrue(diff.has(ALTERED));
        assertTrue(diff.get(ALTERED).has("key1"));
        assertEquals(String.format(ALTERED_FORMAT, "value1", "value1,value2"), diff.get(ALTERED).get("key1").asText());
    }

    @Test
    public void TestDifferenceKeyChangeToObject() throws JsonProcessingException{
        JsonNode from = mapper.readTree("{\n" +
                "  \"key1\":\"value1\"\n" +
                "}");
        JsonNode to = mapper.readTree("{\n" +
                "  \"key1\":{\"key2\": \"value2\", \"key3\": \"value3\"}\n" +
                "}");


        JsonNode diff = DifferenceProcessor.differences(from, to);
        assertTrue(diff.has(IN));
        assertTrue(diff.get(IN).isEmpty());
        assertTrue(diff.has(OUT));
        assertTrue(diff.get(OUT).isEmpty());

        assertTrue(diff.has(ALTERED));
        assertTrue(diff.get(ALTERED).has("key1"));
        assertEquals(String.format(ALTERED_FORMAT, "value1",
                        String.format(KEYED_VALUE_FORMAT, "key2", "value2") + "," + String.format(KEYED_VALUE_FORMAT, "key3", "value3")),
                diff.get(ALTERED).get("key1").asText());
    }

    @Test
    public void TestDifferenceKeyChangeObjectEntries() throws JsonProcessingException{
        JsonNode from = mapper.readTree("{\n" +
                "  \"key1\":{\"key2\": \"value2\", \"key3\": \"value3\"}\n" +
                "}");
        JsonNode to = mapper.readTree("{\n" +
                "  \"key1\":{\"key2\": \"value2\", \"key4\": \"value4\"}\n" +
                "}");


        JsonNode diff = DifferenceProcessor.differences(from, to);
        assertTrue(diff.has(IN));
        assertTrue(diff.get(IN).isEmpty());
        assertTrue(diff.has(OUT));
        assertTrue(diff.get(OUT).isEmpty());

        assertTrue(diff.has(ALTERED));
        assertTrue(diff.get(ALTERED).has("key1"));
        assertEquals(String.format(ALTERED_FORMAT,
                        String.format(KEYED_VALUE_FORMAT, "key2", "value2") + "," + String.format(KEYED_VALUE_FORMAT, "key3", "value3"),
                        String.format(KEYED_VALUE_FORMAT, "key2", "value2") + "," + String.format(KEYED_VALUE_FORMAT, "key4", "value4")),
                diff.get(ALTERED).get("key1").asText());
    }

    @Test
    public void TestDifferenceCombination() throws JsonProcessingException{
        JsonNode from = mapper.readTree("{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key2\":\"value2\",\n" +
                "  \"key3\": [1,2,3],\n" +
                "  \"key4\": {\n" +
                "    \"key5\": \"value5\",\n" +
                "    \"key6\": \"value6\"\n" +
                "  }\n" +
                "}");
        JsonNode to = mapper.readTree("{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key3\": [1,3,4],\n" +
                "  \"key4\": {\n" +
                "    \"key5\": \"value5\",\n" +
                "    \"key7\": \"value7\"\n" +
                "  },\n" +
                "  \"key8\": \"value8\"\n" +
                "}");

        JsonNode diff = DifferenceProcessor.differences(from, to);
        assertTrue(diff.has(IN));
        assertTrue(diff.get(IN).has("key8"));
        assertEquals("value8", diff.get(IN).get("key8").asText());

        assertTrue(diff.has(OUT));
        assertTrue(diff.get(OUT).has("key2"));
        assertEquals("value2", diff.get(OUT).get("key2").asText());

        assertTrue(diff.has(ALTERED));
        assertTrue(diff.get(ALTERED).has("key3"));
        assertEquals(String.format(ALTERED_FORMAT, "1,2,3", "1,3,4"),
                diff.get(ALTERED).get("key3").asText());

        assertTrue(diff.get(ALTERED).has("key4"));
        assertEquals(String.format(ALTERED_FORMAT,
                String.format(KEYED_VALUE_FORMAT, "key5", "value5") + "," + String.format(KEYED_VALUE_FORMAT, "key6", "value6"),
                String.format(KEYED_VALUE_FORMAT, "key5", "value5") + "," + String.format(KEYED_VALUE_FORMAT, "key7", "value7")),
                diff.get(ALTERED).get("key4").asText());
    }

    @Test
    public void TestDifferenceCombinationFromString() throws JsonProcessingException{
        String from = "{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key2\":\"value2\",\n" +
                "  \"key3\": [1,2,3],\n" +
                "  \"key4\": {\n" +
                "    \"key5\": \"value5\",\n" +
                "    \"key6\": \"value6\"\n" +
                "  }\n" +
                "}";
        String to = "{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key3\": [1,3,4],\n" +
                "  \"key4\": {\n" +
                "    \"key5\": \"value5\",\n" +
                "    \"key7\": \"value7\"\n" +
                "  },\n" +
                "  \"key8\": \"value8\"\n" +
                "}";

        JsonNode diff = DifferenceProcessor.differences(from, to);
        assertTrue(diff.has(IN));
        assertTrue(diff.get(IN).has("key8"));
        assertEquals("value8", diff.get(IN).get("key8").asText());

        assertTrue(diff.has(OUT));
        assertTrue(diff.get(OUT).has("key2"));
        assertEquals("value2", diff.get(OUT).get("key2").asText());

        assertTrue(diff.has(ALTERED));
        assertTrue(diff.get(ALTERED).has("key3"));
        assertEquals(String.format(ALTERED_FORMAT, "1,2,3", "1,3,4"),
                diff.get(ALTERED).get("key3").asText());

        assertTrue(diff.get(ALTERED).has("key4"));
        assertEquals(String.format(ALTERED_FORMAT,
                        String.format(KEYED_VALUE_FORMAT, "key5", "value5") + "," + String.format(KEYED_VALUE_FORMAT, "key6", "value6"),
                        String.format(KEYED_VALUE_FORMAT, "key5", "value5") + "," + String.format(KEYED_VALUE_FORMAT, "key7", "value7")),
                diff.get(ALTERED).get("key4").asText());
    }

    @Test
    public void TestSensitiveDifferenceCombinationFromString(){
        String from = "{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key2\":\"value2\",\n" +
                "  \"key3\": [1,2,3],\n" +
                "  \"key4\": {\n" +
                "    \"key5\": \"value5\",\n" +
                "    \"key6\": \"value6\"\n" +
                "  }\n" +
                "}";
        String to = "{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key3\": [1,3,4],\n" +
                "  \"key4\": {\n" +
                "    \"key5\": \"value5\",\n" +
                "    \"key7\": \"value7\"\n" +
                "  },\n" +
                "  \"key8\": \"value8\"\n" +
                "}";

        JsonNode diff = DifferenceProcessor.differences(from, to, true);
        assertTrue(diff.has(IN));
        assertTrue(diff.get(IN).has("key8"));
        assertEquals(HIDDEN_VALUE, diff.get(IN).get("key8").asText());

        assertTrue(diff.has(OUT));
        assertTrue(diff.get(OUT).has("key2"));
        assertEquals(EMPTY_VALUE, diff.get(OUT).get("key2").asText());

        assertTrue(diff.has(ALTERED));
        assertTrue(diff.get(ALTERED).has("key3"));
        assertEquals(HIDDEN_VALUE, diff.get(ALTERED).get("key3").asText());

        assertTrue(diff.get(ALTERED).has("key4"));
        assertEquals(HIDDEN_VALUE, diff.get(ALTERED).get("key4").asText());
    }

    @Test
    public void TestMatch() throws JsonProcessingException{
        JsonNode from = mapper.readTree("{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key2\":\"value2\",\n" +
                "  \"key3\": [1,2,3],\n" +
                "  \"key4\": {\n" +
                "    \"key5\": \"value5\",\n" +
                "    \"key6\": \"value6\"\n" +
                "  }\n" +
                "}");
        JsonNode to = mapper.readTree("{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key2\":\"value2\",\n" +
                "  \"key3\": [1,2,3],\n" +
                "  \"key4\": {\n" +
                "    \"key5\": \"value5\",\n" +
                "    \"key6\": \"value6\"\n" +
                "  }\n" +
                "}");

        assertTrue(DifferenceProcessor.match(from, to));
    }

    @Test
    public void TestUpdateObject() throws JsonProcessingException{
        ObjectNode start = (ObjectNode) mapper.readTree("{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key2\":\"value2\",\n" +
                "  \"key3\": [1,2,3]\n" +
                "}");
        JsonNode merge = mapper.readTree("{\n" +
                "  \"key4\":\"value4\",\n" +
                "  \"key5\":\"value5\",\n" +
                "  \"key3\": [4,5,6]\n" +
                "}");
        JsonNode to = mapper.readTree("{\n" +
                "  \"key1\":\"value1\",\n" +
                "  \"key2\":\"value2\",\n" +
                "  \"key3\": [4,5,6],\n" +
                "  \"key4\":\"value4\",\n" +
                "  \"key5\":\"value5\"\n" +
                "}");
        DifferenceProcessor.updateObjectNode(start, merge);
        assertTrue(DifferenceProcessor.match(start, to));
    }
}
