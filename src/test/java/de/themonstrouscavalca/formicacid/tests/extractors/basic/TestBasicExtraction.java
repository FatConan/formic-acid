package de.themonstrouscavalca.formicacid.tests.extractors.basic;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.*;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

public class TestBasicExtraction{

    @Test
    public void extractString(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "string");
        node.put("testPassword", "password");
        node.put("testPaddedString", "   string     ");
        node.put("testBadType", 10.2);

        StringExtractor extractor = new StringExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testPassword = extractor.extractValidatedValue("testPassword", node, Collections.emptyList());
        ValidatedOptional testPaddedString = extractor.extractValidatedValue("testPaddedString", node, Collections.emptyList());
        ValidatedOptional testBadType = extractor.extractValidatedValue("testBadType", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertEquals("10.2", testBadType.get());
        assertEquals("string", testString.get());
        assertEquals("password", testPassword.get());
        assertEquals("string", testPaddedString.get());
    }

    @Test
    public void extractLong(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "-6");
        node.put("testLong", 7);
        node.put("testBadFormat","10.1");
        node.put("testBadType", true);

        LongExtractor extractor = new LongExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testLong = extractor.extractValidatedValue("testLong", node, Collections.emptyList());
        ValidatedOptional testBadFormat = extractor.extractValidatedValue("testBadFormat", node, Collections.emptyList());
        ValidatedOptional testBadType = extractor.extractValidatedValue("testBadType", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertNull(testBadFormat.get());
        assertNull(testBadType.get());
        assertEquals(-6L, testString.get());
        assertEquals(7L, testLong.get());
    }

    @Test
    public void extractInteger(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "4");
        node.put("testInt", 5);
        node.put("testBadFormat","10.1");
        node.put("testBadType", true);

        IntegerExtractor extractor = new IntegerExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testInt = extractor.extractValidatedValue("testInt", node, Collections.emptyList());
        ValidatedOptional testBadFormat = extractor.extractValidatedValue("testBadFormat", node, Collections.emptyList());
        ValidatedOptional testBadType = extractor.extractValidatedValue("testBadType", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertNull(testBadFormat.get());
        assertNull(testBadType.get());
        assertEquals(4, testString.get());
        assertEquals(5, testInt.get());
    }

    @Test
    public void extractDouble(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "4.123");
        node.put("testDouble", 5.9906);
        node.put("testInt", 6);
        node.put("testIntString", "6");
        node.put("testDoubleZero", 6.0);
        node.put("testBadFormat","10.0.1");
        node.put("testBadType", true);

        DoubleExtractor extractor = new DoubleExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testDouble = extractor.extractValidatedValue("testDouble", node, Collections.emptyList());
        ValidatedOptional testDoubleZero = extractor.extractValidatedValue("testDoubleZero", node, Collections.emptyList());
        ValidatedOptional testInt = extractor.extractValidatedValue("testInt", node, Collections.emptyList());
        ValidatedOptional testIntString = extractor.extractValidatedValue("testIntString", node, Collections.emptyList());
        ValidatedOptional testBadFormat = extractor.extractValidatedValue("testBadFormat", node, Collections.emptyList());
        ValidatedOptional testBadType = extractor.extractValidatedValue("testBadType", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertNull(testBadFormat.get());
        assertNull(testBadType.get());
        assertEquals(6.0, testInt.get());
        assertEquals(6.0, testDoubleZero.get());
        assertEquals(6.0, testIntString.get());
        assertEquals(4.123, testString.get());
        assertEquals(5.9906, testDouble.get());
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
        ValidatedOptional<Boolean> testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional<Boolean> testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional<Boolean> testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional<Boolean> testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional<Boolean> testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional<Boolean> testBoolean = extractor.extractValidatedValue("testBoolean", node, Collections.emptyList());
        ValidatedOptional<Boolean> testInt = extractor.extractValidatedValue("testInt", node, Collections.emptyList());
        ValidatedOptional<Boolean> testAlternative = extractor.extractValidatedValue("testAlternative", node, Collections.emptyList());

        assert(testEmpty.isPresent());
        assert(testNull.isPresent());
        assert(testWhitespace.isPresent());
        assert(!testNoHit.isPresent());
        assertNull(testNoHit.get());
        assertNull(testEmpty.get());
        assertNull(testNull.get());
        assertNull(testWhitespace.get());
        assertEquals(Boolean.TRUE, testString.get());
        assertEquals(Boolean.FALSE, testBoolean.get());
        assertEquals(Boolean.TRUE, testInt.get());
        assertNull(testAlternative.get());
    }

    @Test
    public void extractLocalDate(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "2015-01-29");
        node.put("testString2", "2020-07-31");
        node.put("testInvalidString", "2018-12-32");
        node.put("testIncorrectString", "incorrect");

        LocalDateExtractor extractor = new LocalDateExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testString2 = extractor.extractValidatedValue("testString2", node, Collections.emptyList());
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
        assertEquals(LocalDate.of(2015, 1, 29), testString.get());
        assertEquals(LocalDate.of(2020, 7, 31), testString2.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }

    @Test
    public void extractLocalTime(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "20:30");
        node.put("testString2", "05:25");
        node.put("testInvalidString", "25:00");
        node.put("testIncorrectString", "incorrect");

        LocalTimeExtractor extractor = new LocalTimeExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testString2 = extractor.extractValidatedValue("testString2", node, Collections.emptyList());
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
        assertEquals(LocalTime.of(20, 30), testString.get());
        assertEquals(LocalTime.of(5, 25), testString2.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }

    @Test
    public void extractLocalDateTime(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");
        node.put("testString", "2015-01-29 11:59");
        node.put("testString2", "2020-07-31 07:49");
        node.put("testInvalidString", "2018-12-32");
        node.put("testIncorrectString", "incorrect");

        LocalDateTimeExtractor extractor = new LocalDateTimeExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testString2 = extractor.extractValidatedValue("testString2", node, Collections.emptyList());
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
        assertEquals(LocalDateTime.of(2015, 1, 29, 11, 59), testString.get());
        assertEquals(LocalDateTime.of(2020, 7, 31, 7, 49), testString2.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }
}
