package de.themonstrouscavalca.formicacid.tests.validation.basic;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.tests.validation.ValidationBase;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;
import de.themonstrouscavalca.formicacid.validators.impl.replacement.DefaultingValidator;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;


public class DefaultingValidatorTest extends ValidationBase{
    @Test
    public void requiredValidatorTest(){
        final String nulledKey = "nulledValue";
        final String presentKey = "presentValue";
        final String missingKey = "missingKey";

        ObjectNode data = JsonNodeFactory.instance.objectNode();
        data.putNull(nulledKey);
        data.put(presentKey, Boolean.FALSE);

        ValidatedOptional<Boolean> booleanTest = booleanExtractor.extractValidatedValue(nulledKey, data,  Collections.singletonList(new DefaultingValidator<>(Boolean.TRUE)));
        assert(booleanTest.isValid());
        assertEquals(Boolean.TRUE, booleanTest.get());
        assert(booleanTest.isPresent());

        booleanTest = booleanExtractor.extractValidatedValue(presentKey, data,  Collections.singletonList(new DefaultingValidator<>(Boolean.TRUE)));
        assert(booleanTest.isValid());
        assertEquals(Boolean.FALSE, booleanTest.get());
        assert(booleanTest.isPresent());

        booleanTest = booleanExtractor.extractValidatedValue(missingKey, data,  Collections.singletonList(new DefaultingValidator<>(Boolean.TRUE)));
        assert(booleanTest.isValid());
        assertEquals(Boolean.TRUE, booleanTest.get());
        assert(booleanTest.isPresent());
    }
}
