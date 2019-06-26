package de.themonstrouscavalca.formicacid.tests.validation.basic;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.tests.validation.ValidationBase;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;
import de.themonstrouscavalca.formicacid.validators.impl.basic.RequiredValidator;
import de.themonstrouscavalca.formicacid.validators.impl.numeric.NonZeroValidator;
import de.themonstrouscavalca.formicacid.validators.impl.numeric.NumericBoundsValidator;
import de.themonstrouscavalca.formicacid.validators.impl.strings.*;
import de.themonstrouscavalca.formicacid.validators.impl.strings.dates.LocalDateStringValidator;
import de.themonstrouscavalca.formicacid.validators.impl.strings.dates.LocalDateTimeStringValidator;
import de.themonstrouscavalca.formicacid.validators.impl.strings.dates.LocalTimeStringValidator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class RequiredValidationTests extends ValidationBase{
    @Test
    public void requiredValidatorTest(){
        final String nulledKey = "nulledValue";
        final String missingKey = "missingValue";
        final String invalidKey = "invalidValue";
        final String presentKey = "presentValue";
        final String zeroKey = "zeroValue";

        ObjectNode data = JsonNodeFactory.instance.objectNode();
        data.putNull(nulledKey);
        data.put(presentKey, "PRESENT");

        //Test Strings
        ValidatedOptional<String> stringTest = stringExtractor.extractValidatedValue(nulledKey, data,  Collections.singletonList(new RequiredValidator<>("REQUIRED")));
        assertFalse(stringTest.isValid());
        assertEquals("REQUIRED", stringTest.getErrors()[0]);

        stringTest = stringExtractor.extractValidatedValue(missingKey, data, Collections.singletonList(new RequiredValidator<>("REQUIRED")));
        assertFalse(stringTest.isValid());
        assertEquals("REQUIRED", stringTest.getErrors()[0]);

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Collections.singletonList(new RequiredValidator<>("REQUIRED")));
        assertTrue(stringTest.isValid());

        //Specific String Validators
        //Emails
        data.put(presentKey, "test@example.org");
        data.put(invalidKey, "test@example.org.");

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new EmailStringValidator()));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new EmailStringValidator()));
        assertFalse(stringTest.isValid());
        assertEquals("This is not a valid e-mail address", stringTest.getErrors()[0]);

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new EmailStringValidator("BROKEN EMAIL")));
        assertFalse(stringTest.isValid());
        assertEquals("BROKEN EMAIL", stringTest.getErrors()[0]);

        //HipChat(or other chat, @ symbol followed by username regex)
        data.put(presentKey, "@example.org");
        data.put(invalidKey, "test@example.org.");

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new HipChatHandleValidator()));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new HipChatHandleValidator()));
        assertFalse(stringTest.isValid());
        assertEquals("This must start with an @ and may not contain any whitespace", stringTest.getErrors()[0]);

        //Regex String Validator
        data.put(presentKey, "King-kong1");
        data.put(invalidKey, "King-1kong");

        String regex = "King-[A-Za-z]+[0-9]+";
        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new RegexStringValidator(Pattern.compile(regex))));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new RegexStringValidator(Pattern.compile(regex))));
        assertFalse(stringTest.isValid());
        assertEquals("The provided value is not in the correct format", stringTest.getErrors()[0]);

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new RegexStringValidator(Pattern.compile(regex), "DOESNT MATCH REGEX")));
        assertFalse(stringTest.isValid());
        assertEquals("DOESNT MATCH REGEX", stringTest.getErrors()[0]);

        //Clash test and one of tests
        data.put(presentKey, "CCC");
        data.put(invalidKey, "AAA");

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new StringDoesntClashValidator("CLASHES", "AAA", "BBB")));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new StringDoesntClashValidator("CLASHES", "AAA", "BBB", "CCC")));
        assertFalse(stringTest.isValid());
        assertEquals("CLASHES", stringTest.getErrors()[0]);

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new StringOneOfValidator("NOT FOUND", "AAA", "BBB", "CCC")));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new StringOneOfValidator("NOT FOUND", "BBB", "CCC")));
        assertFalse(stringTest.isValid());
        assertEquals("NOT FOUND", stringTest.getErrors()[0]);

        //Telephone Number tests
        data.put(presentKey, "+44 0700 900 900");
        data.put(invalidKey, "cheese 89834234234");

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new TelephoneNumberValidator()));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new TelephoneNumberValidator()));
        assertFalse(stringTest.isValid());
        assertEquals( "This is not a valid phone number", stringTest.getErrors()[0]);

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new TelephoneNumberValidator("WRONG PHONE")));
        assertFalse(stringTest.isValid());
        assertEquals("WRONG PHONE", stringTest.getErrors()[0]);

        //URL String tests
        data.put(presentKey, "https://www.themonstrouscavalca.de");
        data.put(invalidKey, "ftp://ftp.example.org");

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new URLStringValidator()));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new URLStringValidator()));
        assertFalse(stringTest.isValid());
        assertEquals( "This is not a valid URL", stringTest.getErrors()[0]);

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new URLStringValidator("INVALID URL")));
        assertFalse(stringTest.isValid());
        assertEquals("INVALID URL", stringTest.getErrors()[0]);

        //String dates
        data.put(presentKey, "2019-06-01");
        data.put(invalidKey, "2019-12-41");

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new LocalDateStringValidator()));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new LocalDateStringValidator()));
        assertFalse(stringTest.isValid());
        assertEquals("The provided value is not parsable as a date", stringTest.getErrors()[0]);

        data.put(invalidKey, "INVALID");

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new LocalDateStringValidator()));
        assertFalse(stringTest.isValid());
        assertEquals("The provided value is not parsable as a date", stringTest.getErrors()[0]);

        //String times
        data.put(presentKey, "12:00");
        data.put(invalidKey, "24:59");

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new LocalTimeStringValidator()));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new LocalTimeStringValidator()));
        assertFalse(stringTest.isValid());
        assertEquals("The provided value is not parsable as a time", stringTest.getErrors()[0]);

        data.put(invalidKey, "INVALID");

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new LocalTimeStringValidator()));
        assertFalse(stringTest.isValid());
        assertEquals("The provided value is not parsable as a time", stringTest.getErrors()[0]);

        //String times
        data.put(presentKey, "2019-06-01 12:00");
        data.put(invalidKey, "2019-12-41 24:59");

        stringTest = stringExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new LocalDateTimeStringValidator()));
        assertTrue(stringTest.isValid());

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new LocalDateTimeStringValidator()));
        assertFalse(stringTest.isValid());
        assertEquals("The provided value is not parsable as a datetime", stringTest.getErrors()[0]);

        data.put(invalidKey, "INVALID");

        stringTest = stringExtractor.extractValidatedValue(invalidKey, data, Arrays.asList(new RequiredValidator<>(), new LocalDateTimeStringValidator()));
        assertFalse(stringTest.isValid());
        assertEquals("The provided value is not parsable as a datetime", stringTest.getErrors()[0]);

        //Long Tests
        data.put(invalidKey, "INVALID");
        data.put(presentKey, 6L);
        data.put(zeroKey, 0L);

        ValidatedOptional<Long> longTest = longExtractor.extractValidatedValue(nulledKey, data,  Collections.singletonList(new RequiredValidator<>("REQUIRED")));
        assertFalse(longTest.isValid());
        assertEquals("REQUIRED", longTest.getErrors()[0]);

        longTest = longExtractor.extractValidatedValue(missingKey, data,  Collections.singletonList(new RequiredValidator<>("REQUIRED")));
        assertFalse(longTest.isValid());
        assertEquals("REQUIRED", longTest.getErrors()[0]);

        longTest = longExtractor.extractValidatedValue(invalidKey, data,  Collections.singletonList(new RequiredValidator<>("REQUIRED")));
        assertFalse(longTest.isValid());
        assertEquals("REQUIRED", longTest.getErrors()[1]);

        longTest = longExtractor.extractValidatedValue(presentKey, data,  Collections.singletonList(new RequiredValidator<>("REQUIRED")));
        assertTrue(longTest.isValid());

        longTest = longExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NumericBoundsValidator<>(1L, 9L)));
        assertTrue(longTest.isValid());

        longTest = longExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NumericBoundsValidator<>(10L, 20L)));
        assertFalse(longTest.isValid());
        assertEquals("You must enter a value between 10 and 20", longTest.getErrors()[0]);

        longTest = longExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NumericBoundsValidator<>(7L, 8L, "OUT OF BOUNDS")));
        assertFalse(longTest.isValid());
        assertEquals("OUT OF BOUNDS", longTest.getErrors()[0]);

        longTest = longExtractor.extractValidatedValue(zeroKey, data, Arrays.asList(new RequiredValidator<>(), new NonZeroValidator<>()));
        assertFalse(longTest.isValid());
        assertEquals("You must enter a non zero value", longTest.getErrors()[0]);

        longTest = longExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NonZeroValidator<>()));
        assertTrue(longTest.isValid());

        //Double Tests
        data.put(invalidKey, "INVALID");
        data.put(presentKey, 6.0);
        data.put(zeroKey, 0.0);

        ValidatedOptional<Double> doubleTest = doubleExtractor.extractValidatedValue(nulledKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(doubleTest.isValid());
        assertEquals("This is a required field", doubleTest.getErrors()[0]);

        doubleTest = doubleExtractor.extractValidatedValue(missingKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(doubleTest.isValid());
        assertEquals("This is a required field", doubleTest.getErrors()[0]);

        doubleTest = doubleExtractor.extractValidatedValue(invalidKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(doubleTest.isValid());
        assertEquals("This is a required field", doubleTest.getErrors()[1]);

        doubleTest = doubleExtractor.extractValidatedValue(presentKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertTrue(doubleTest.isValid());

        doubleTest = doubleExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NumericBoundsValidator<>(1.0, 9.9)));
        assertTrue(doubleTest.isValid());

        doubleTest = doubleExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NumericBoundsValidator<>(10.0, 20.0)));
        assertFalse(doubleTest.isValid());
        assertEquals("You must enter a value between 10.000000 and 20.000000", doubleTest.getErrors()[0]);

        doubleTest = doubleExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NumericBoundsValidator<>(6.1, 8.0, "OUT OF BOUNDS")));
        assertFalse(doubleTest.isValid());
        assertEquals("OUT OF BOUNDS", doubleTest.getErrors()[0]);

        doubleTest = doubleExtractor.extractValidatedValue(zeroKey, data, Arrays.asList(new RequiredValidator<>(), new NonZeroValidator<>()));
        assertFalse(doubleTest.isValid());
        assertEquals("You must enter a non zero value", doubleTest.getErrors()[0]);

        doubleTest = doubleExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NonZeroValidator<>()));
        assertTrue(doubleTest.isValid());

        //Integer Tests
        data.put(invalidKey, "INVALID");
        data.put(presentKey, 60);
        data.put(zeroKey, 0);

        ValidatedOptional<Integer> integerTest = integerExtractor.extractValidatedValue(nulledKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(integerTest.isValid());
        assertEquals("This is a required field", integerTest.getErrors()[0]);

        integerTest = integerExtractor.extractValidatedValue(missingKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(integerTest.isValid());
        assertEquals("This is a required field", integerTest.getErrors()[0]);

        integerTest = integerExtractor.extractValidatedValue(invalidKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(integerTest.isValid());
        assertEquals("This is a required field", integerTest.getErrors()[1]);

        integerTest = integerExtractor.extractValidatedValue(presentKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertTrue(integerTest.isValid());

        integerTest = integerExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NumericBoundsValidator<>(0, 70)));
        assertTrue(integerTest.isValid());

        integerTest = integerExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NumericBoundsValidator<>(10, 20)));
        assertFalse(integerTest.isValid());
        assertEquals("You must enter a value between 10 and 20", integerTest.getErrors()[0]);

        integerTest = integerExtractor.extractValidatedValue(presentKey, data, Arrays.asList(new RequiredValidator<>(), new NumericBoundsValidator<>(61, 80, "OUT OF BOUNDS")));
        assertFalse(integerTest.isValid());
        assertEquals("OUT OF BOUNDS", integerTest.getErrors()[0]);

        integerTest = integerExtractor.extractValidatedValue(zeroKey, data, Arrays.asList(new RequiredValidator<>(), new NonZeroValidator<>()));
        assertFalse(integerTest.isValid());
        assertEquals("You must enter a non zero value", integerTest.getErrors()[0]);

        //Boolean Tests
        data.put(invalidKey, "INVALID");
        data.put(presentKey, "on");

        ValidatedOptional<Boolean> booleanTest = booleanExtractor.extractValidatedValue(nulledKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(booleanTest.isValid());
        assertEquals("This is a required field", booleanTest.getErrors()[0]);

        booleanTest = booleanExtractor.extractValidatedValue(missingKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(booleanTest.isValid());
        assertEquals("This is a required field", booleanTest.getErrors()[0]);

        booleanTest = booleanExtractor.extractValidatedValue(invalidKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(booleanTest.isValid());
        assertEquals("This is a required field", booleanTest.getErrors()[1]);

        booleanTest = booleanExtractor.extractValidatedValue(presentKey, data,  Collections.singletonList(new RequiredValidator<>()));
        assertFalse(booleanTest.isValid());
        assertTrue(booleanTest.isPresentInJson());
    }

}
