package de.themonstrouscavalca.formicacid.tests.validation.basic;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.tests.validation.ValidationBase;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;
import de.themonstrouscavalca.formicacid.validators.impl.basic.RequiredValidator;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class RequiredValidationTests extends ValidationBase{
    @Test
    public void requiredValidatorTest(){
        final String nulledKey = "nulledValue";
        final String missingKey = "missingValue";
        final String invalidKey = "invalidValue";
        final String presentKey = "presentValue";

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

        //Long Tests
        data.put(invalidKey, "INVALID");
        data.put(presentKey, 6L);

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

        //Double Tests
        data.put(invalidKey, "INVALID");
        data.put(presentKey, 6.0);

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

        //Integer Tests
        data.put(invalidKey, "INVALID");
        data.put(presentKey, 60);

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
        assertTrue(booleanTest.isValid());

    }

}
