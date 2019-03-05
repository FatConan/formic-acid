package de.themonstrouscavalca.formicacid.tests.extractors.collections;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.extractors.impl.collection.*;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class TestCollectionExtractors{
    public class CheckList<T>{
        private void checkLists(List<T> expected, List<T> actual){
            for(int i=0; i < expected.size(); i++){
                try{
                    assertEquals(expected.get(i), actual.get(i));
                }catch(IndexOutOfBoundsException e){
                    assert(false);
                }
            }

            for(int i=0; i < actual.size(); i++){
                try{
                    assertEquals(expected.get(i), actual.get(i));
                }catch(IndexOutOfBoundsException e){
                    assert(false);
                }
            }
        }
    }

    @Test
    public void extractStringList(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");

        ArrayNode testList = node.putArray("testString");
        testList.add("alpha");
        testList.add("beta");
        testList.add("gamma");
        testList.add("delta");
        testList.add("epsilon");

        node.put("testInvalidString", "[1,2,3,4,5]");
        node.put("testIncorrectString", "incorrect");

        StringListExtractor extractor = new StringListExtractor();
        CheckList<String> longCheckList = new CheckList<>();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional<List<String>> testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
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
        longCheckList.checkLists(Arrays.asList("alpha", "beta", "gamma", "delta", "epsilon"), testString.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }


    @Test
    public void extractLongList(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");

        ArrayNode testList = node.putArray("testString");
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("4");
        testList.add("5");

        ArrayNode testListLong = node.putArray("testLongs");
        testListLong.add(1L);
        testListLong.add(2L);
        testListLong.add(3L);
        testListLong.add(4L);
        testListLong.add(5L);

        node.put("testInvalidString", "[1,2,3,4,5]");
        node.put("testIncorrectString", "incorrect");

        LongListExtractor extractor = new LongListExtractor();
        CheckList<Long> longCheckList = new CheckList<>();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional<List<Long>> testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional<List<Long>> testString2 = extractor.extractValidatedValue("testLongs", node, Collections.emptyList());
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
        longCheckList.checkLists(Arrays.asList(1L, 2L, 3L, 4L, 5L), testString.get());
        longCheckList.checkLists(Arrays.asList(1L, 2L, 3L, 4L, 5L), testString2.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }

    @Test
    public void extractIntegerList(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");

        ArrayNode testList = node.putArray("testString");
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("4");
        testList.add("5");

        ArrayNode testListLong = node.putArray("testLongs");
        testListLong.add(1);
        testListLong.add(2);
        testListLong.add(3);
        testListLong.add(4);
        testListLong.add(5);

        node.put("testInvalidString", "[1,2,3,4,5]");
        node.put("testIncorrectString", "incorrect");

        IntegerListExtractor extractor = new IntegerListExtractor();
        CheckList<Integer> integerCheckList = new CheckList<>();

        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional<List<Integer>> testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional<List<Integer>> testString2 = extractor.extractValidatedValue("testLongs", node, Collections.emptyList());
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
        integerCheckList.checkLists(Arrays.asList(1, 2, 3, 4, 5), testString.get());
        integerCheckList.checkLists(Arrays.asList(1, 2, 3, 4, 5), testString2.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }

    @Test
    public void extractBooleanList(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");

        ArrayNode testList = node.putArray("testString");
        testList.add("true");
        testList.add("True");
        testList.add("False");
        testList.add("false");
        testList.add("TRUE");

        ArrayNode testListLong = node.putArray("testBoolean");
        testListLong.add(true);
        testListLong.add(true);
        testListLong.add(false);
        testListLong.add(false);
        testListLong.add(true);

        ArrayNode testPartialBooleans = node.putArray("testPartialBoolean");
        testPartialBooleans.add(true);
        testPartialBooleans.add(true);
        testPartialBooleans.addNull();
        testPartialBooleans.add(false);
        testPartialBooleans.add(true);

        node.put("testInvalidString", "[true,true,false,false,true]");
        node.put("testIncorrectString", "incorrect");

        BooleanListExtractor extractor = new BooleanListExtractor();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional testBoolean = extractor.extractValidatedValue("testBoolean", node, Collections.emptyList());
        ValidatedOptional testPartialBoolean = extractor.extractValidatedValue("testPartialBoolean", node, Collections.emptyList());
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
        assertEquals(Arrays.asList(true, true, false, false, true), testString.get());
        assertEquals(Arrays.asList(true, true, false, true), testPartialBoolean.get());
        assertEquals(Arrays.asList(true, true, false, false, true), testBoolean.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }

    @Test
    public void extractDoubleList(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");

        ArrayNode testList = node.putArray("testString");
        testList.add("0.2");
        testList.add("0.3");
        testList.add("0.5");
        testList.add("0.7");
        testList.add("0.11");

        ArrayNode testListLong = node.putArray("testDoubles");
        testListLong.add(0.2);
        testListLong.add(0.3);
        testListLong.add(0.5);
        testListLong.add(0.7);
        testListLong.add(0.11);

        node.put("testInvalidString", "[0.2,0.3,0.5,0.7,0.11]");
        node.put("testIncorrectString", "incorrect");

        DoubleListExtractor extractor = new DoubleListExtractor();
        CheckList<Double> doubleCheckList = new CheckList<>();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional<List<Double>> testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional<List<Double>> testDoubles = extractor.extractValidatedValue("testDoubles", node, Collections.emptyList());
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
        doubleCheckList.checkLists(Arrays.asList(0.2, 0.3, 0.5, 0.7, 0.11), testString.get());
        doubleCheckList.checkLists(Arrays.asList(0.2, 0.3, 0.5, 0.7, 0.11), testDoubles.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }

    @Test
    public void extractLocalDateList(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");

        ArrayNode testList = node.putArray("testString");
        testList.add("2018-01-01");
        testList.add("2018-02-02");
        testList.add("2018-03-01");
        testList.add("2018-04-13");
        testList.add("2018-12-31");

        node.put("testInvalidString", "[2018-01-01,2018-02-02,2018-03-01,2018-04-13,2018-12-31]");
        node.put("testIncorrectString", "incorrect");

        LocalDateListExtractor extractor = new LocalDateListExtractor();
        CheckList<LocalDate> doubleCheckList = new CheckList<>();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional<List<LocalDate>> testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
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
        doubleCheckList.checkLists(Arrays.asList(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 2, 2),
                LocalDate.of(2018, 3, 1), LocalDate.of(2018, 4, 13), LocalDate.of(2018, 12, 31)), testString.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }


    @Test
    public void extractLocalDateTimeList(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");

        ArrayNode testList = node.putArray("testString");
        testList.add("2018-01-01 13:00:00");
        testList.add("2018-02-02 14:00:00");
        testList.add("2018-03-01 15:30:00");
        testList.add("2018-04-13 20:45:15");
        testList.add("2018-12-31 23:59:59");

        ArrayNode testListNoSeconds = node.putArray("testStringNoSeconds");
        testListNoSeconds.add("2018-01-01 13:00");
        testListNoSeconds.add("2018-02-02 14:00");
        testListNoSeconds.add("2018-03-01 15:30");
        testListNoSeconds.add("2018-04-13 20:45");
        testListNoSeconds.add("2018-12-31 23:59");

        node.put("testInvalidString", "[2018-01-01 13:00:00," +
                "2018-02-02 14:00:00," +
                "2018-03-01 15:30:00," +
                "2018-04-13 20:45:00," +
                "2018-12-31 23:59:59]");
        node.put("testIncorrectString", "incorrect");

        LocalDateTimeListExtractor noSecondsExtractor = new LocalDateTimeListExtractor();
        LocalDateTimeListExtractor extractor = new LocalDateTimeListExtractor(true);
        CheckList<LocalDateTime> localDateTimeCheckList = new CheckList<>();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional<List<LocalDateTime>> testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional<List<LocalDateTime>> testStringNoSeconds = noSecondsExtractor.extractValidatedValue("testStringNoSeconds", node, Collections.emptyList());
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
        localDateTimeCheckList.checkLists(Arrays.asList(
                LocalDateTime.of(2018, 1, 1, 13, 0, 0),
                LocalDateTime.of(2018, 2, 2, 14, 0, 0),
                LocalDateTime.of(2018, 3, 1, 15, 30, 0),
                LocalDateTime.of(2018, 4, 13, 20, 45, 15),
                LocalDateTime.of(2018, 12, 31, 23, 59, 59)), testString.get());
        localDateTimeCheckList.checkLists(Arrays.asList(
                LocalDateTime.of(2018, 1, 1, 13, 0, 0),
                LocalDateTime.of(2018, 2, 2, 14, 0, 0),
                LocalDateTime.of(2018, 3, 1, 15, 30, 0),
                LocalDateTime.of(2018, 4, 13, 20, 45, 0),
                LocalDateTime.of(2018, 12, 31, 23, 59, 0)), testStringNoSeconds.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }


    @Test
    public void extractLocalTimeList(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("testEmpty", "");
        node.putNull("testNull");
        node.put("testWhitespace", "        ");

        ArrayNode testList = node.putArray("testString");
        testList.add("13:00:00");
        testList.add("14:00:00");
        testList.add("15:30:00");
        testList.add("20:45:14");
        testList.add("23:59:59");

        ArrayNode testListNoSeconds = node.putArray("testStringNoSeconds");
        testListNoSeconds.add("13:00");
        testListNoSeconds.add("14:00");
        testListNoSeconds.add("15:30");
        testListNoSeconds.add("20:45");
        testListNoSeconds.add("23:59");

        node.put("testInvalidString", "[13:00:00,14:00:00,15:30:00,20:45:00,23:59:59]");
        node.put("testIncorrectString", "incorrect");

        LocalTimeListExtractor extractorNoSeconds = new LocalTimeListExtractor();
        LocalTimeListExtractor extractor = new LocalTimeListExtractor(true);
        CheckList<LocalTime> localTimeCheckList = new CheckList<>();
        ValidatedOptional testNoHit = extractor.extractValidatedValue("notPresent", node, Collections.emptyList());
        ValidatedOptional testEmpty = extractor.extractValidatedValue("testEmpty", node, Collections.emptyList());
        ValidatedOptional testNull = extractor.extractValidatedValue("testNull", node, Collections.emptyList());
        ValidatedOptional testWhitespace = extractor.extractValidatedValue("testWhitespace", node, Collections.emptyList());
        ValidatedOptional<List<LocalTime>> testString = extractor.extractValidatedValue("testString", node, Collections.emptyList());
        ValidatedOptional<List<LocalTime>> testStringNoSeconds = extractorNoSeconds.extractValidatedValue("testStringNoSeconds", node, Collections.emptyList());
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
        localTimeCheckList.checkLists(Arrays.asList(
                LocalTime.of(13, 0, 0),
                LocalTime.of(14, 0, 0),
                LocalTime.of(15, 30, 0),
                LocalTime.of(20, 45, 14),
                LocalTime.of(23, 59, 59)), testString.get());
        localTimeCheckList.checkLists(Arrays.asList(
                LocalTime.of(13, 0, 0),
                LocalTime.of(14, 0, 0),
                LocalTime.of(15, 30, 0),
                LocalTime.of(20, 45, 0),
                LocalTime.of(23, 59, 0)), testStringNoSeconds.get());
        assertNull(testInvalidString.get());
        assertNull(testIncorrectString.get());
    }

}
