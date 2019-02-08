package de.themonstrouscavalca.formicacid.tests;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.*;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import java.time.LocalTime;
import java.util.Collections;

public class TestExtraction{


    @Test
    public void extractString(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "string");
        node.put("testPassword", "password");
        node.put("testPaddedString", "   string     ");

        StringExtractor extractor = new StringExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testPassword = extractor.extractValidatedValue("testPassword", node, Collections.emptyList());
        ValidatedOptional testPaddedString = extractor.extractValidatedValue("testPaddedString", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertEquals(testString.get(), "string");
        assertEquals(testPassword.get(), "password");
        assertEquals(testPaddedString.get(), "string");
    }

    @Test
    public void extractLong(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "6");
        node.put("testLong", 7);

        LongExtractor extractor = new LongExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testLong = extractor.extractValidatedValue("testLong", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertEquals(testString.get(), 6L);
        assertEquals(testLong.get(), 7L);
    }

    @Test
    public void extractInteger(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "4");
        node.put("testInt", 5);

        IntegerExtractor extractor = new IntegerExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testInt = extractor.extractValidatedValue("testInt", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertEquals(testString.get(), 4);
        assertEquals(testInt.get(), 5);
    }

    @Test
    public void extractDouble(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "4.123");
        node.put("testDouble", 5.9906);

        DoubleExtractor extractor = new DoubleExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testDouble = extractor.extractValidatedValue("testDouble", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertEquals(testString.get(), 4.123);
        assertEquals(testDouble.get(), 5.9906);
    }

    @Test
    public void extractBoolean(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "true");
        node.put("testBoolean", false);
        node.put("testInt", 1);
        node.put("testAlternative", "on");

        BooleanExtractor extractor = new BooleanExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testBoolean = extractor.extractValidatedValue("testBoolean", node, Collections.emptyList());
        ValidatedOptional testInt = extractor.extractValidatedValue("testInt", node, Collections.emptyList());
        ValidatedOptional testAlternative = extractor.extractValidatedValue("testAlternative", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertEquals(testString.get(), Boolean.TRUE);
        assertEquals(testBoolean.get(), Boolean.FALSE);
        assertEquals(testInt.get(), Boolean.TRUE);
        assertEquals(testAlternative.get(), Boolean.FALSE);
    }

    @Test
    public void extractLocalTime(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "20:30");
        node.put("testInvalidString", "25:00");
        node.put("testIncorrectString", "incorrect");

        LocalTimeExtractor extractor = new LocalTimeExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testInvalidString = extractor.extractValidatedValue("testInvalidString", node, Collections.emptyList());
        ValidatedOptional testIncorrectString = extractor.extractValidatedValue("testIncorrectString", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertEquals(testString.get(), LocalTime.of(20, 30));
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }
}
