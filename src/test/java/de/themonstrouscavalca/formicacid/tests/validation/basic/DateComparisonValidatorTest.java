package de.themonstrouscavalca.formicacid.tests.validation.basic;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.tests.validation.ValidationBase;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.datetime.RelativeDateTimeComparator;
import de.themonstrouscavalca.formicacid.validators.impl.datetime.RelativeDateTimesValidator;
import de.themonstrouscavalca.formicacid.validators.impl.datetime.RelativeDatesValidator;
import de.themonstrouscavalca.formicacid.validators.impl.datetime.RelativeTimesValidator;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.Assert.*;


public class DateComparisonValidatorTest extends ValidationBase{
    @Test
    public void dateComparisonValidatorTest(){
        final String nulledKey = "nulledValue";
        final String presentKey = "presentValue";
        final String secondPresentKey = "secondPresentValue";

        ObjectNode data = JsonNodeFactory.instance.objectNode();
        data.putNull(nulledKey);

        //Relative Dates
        data.put(presentKey, "2019-06-01");
        data.put(secondPresentKey, "2019-06-05");

        IntermediateValidateOptional<LocalDate> firstDate = dateExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        IntermediateValidateOptional<LocalDate> secondDate = dateExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDatesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.LESS_THAN));
        assertTrue(firstDate.isValid());

        firstDate = dateExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDatesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.GREATER_THAN));
        assertFalse(firstDate.isValid());

        firstDate = dateExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDatesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.NOT_EQUALS));
        assertTrue(firstDate.isValid());

        data.put(presentKey, "2019-06-01");
        data.put(secondPresentKey, "2019-06-01");

        firstDate = dateExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDatesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.LESS_THAN));
        assertFalse(firstDate.isValid());

        firstDate = dateExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDatesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.GREATER_THAN));
        assertFalse(firstDate.isValid());

        firstDate = dateExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDatesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.GREATER_THAN_OR_EQUAL));
        assertTrue(firstDate.isValid());

        firstDate = dateExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDatesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.LESS_THAN_OR_EQUAL));
        assertTrue(firstDate.isValid());

        firstDate = dateExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDatesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.EQUALS));
        assertTrue(firstDate.isValid());
    }

    @Test
    public void timeComparisonValidatorTest(){
        final String nulledKey = "nulledValue";
        final String presentKey = "presentValue";
        final String secondPresentKey = "secondPresentValue";

        ObjectNode data = JsonNodeFactory.instance.objectNode();
        data.putNull(nulledKey);

        //Relative Dates
        data.put(presentKey, "12:31");
        data.put(secondPresentKey, "15:12");

        IntermediateValidateOptional<LocalTime> firstDate = timeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        IntermediateValidateOptional<LocalTime> secondDate = timeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = timeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.LESS_THAN));
        assertTrue(firstDate.isValid());

        firstDate = timeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = timeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = timeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.GREATER_THAN));
        assertFalse(firstDate.isValid());

        firstDate = timeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = timeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = timeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.NOT_EQUALS));
        assertTrue(firstDate.isValid());

        data.put(presentKey, "00:45");
        data.put(secondPresentKey, "00:45");

        firstDate = timeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = timeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = timeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.LESS_THAN));
        assertFalse(firstDate.isValid());

        firstDate = timeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = timeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = timeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.GREATER_THAN));
        assertFalse(firstDate.isValid());

        firstDate = timeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = timeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = timeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.GREATER_THAN_OR_EQUAL));
        assertTrue(firstDate.isValid());

        firstDate = timeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = timeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = timeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.LESS_THAN_OR_EQUAL));
        assertTrue(firstDate.isValid());

        firstDate = timeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = timeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = timeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.EQUALS));
        assertTrue(firstDate.isValid());
    }

    @Test
    public void datetimeComparisonValidatorTest(){
        final String nulledKey = "nulledValue";
        final String presentKey = "presentValue";
        final String secondPresentKey = "secondPresentValue";

        ObjectNode data = JsonNodeFactory.instance.objectNode();
        data.putNull(nulledKey);

        //Relative Dates
        data.put(presentKey, "2019-06-01 12:31");
        data.put(secondPresentKey, "2019-06-05 12:31");

        IntermediateValidateOptional<LocalDateTime> firstDate = dateTimeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        IntermediateValidateOptional<LocalDateTime> secondDate = dateTimeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateTimeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDateTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.LESS_THAN));
        assertTrue(firstDate.isValid());

        firstDate = dateTimeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateTimeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateTimeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDateTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.GREATER_THAN));
        assertFalse(firstDate.isValid());

        firstDate = dateTimeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateTimeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateTimeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDateTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.NOT_EQUALS));
        assertTrue(firstDate.isValid());

        data.put(presentKey, "2019-06-01 00:45");
        data.put(secondPresentKey, "2019-06-01 00:45");

        firstDate = dateTimeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateTimeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateTimeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDateTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.LESS_THAN));
        assertFalse(firstDate.isValid());

        firstDate = dateTimeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateTimeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateTimeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDateTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.GREATER_THAN));
        assertFalse(firstDate.isValid());

        firstDate = dateTimeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateTimeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateTimeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDateTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.GREATER_THAN_OR_EQUAL));
        assertTrue(firstDate.isValid());

        firstDate = dateTimeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateTimeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateTimeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDateTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.LESS_THAN_OR_EQUAL));
        assertTrue(firstDate.isValid());

        firstDate = dateTimeExtractor.extractFinalIntermediate(presentKey, data, Collections.emptyList());
        secondDate = dateTimeExtractor.extractFinalIntermediate(secondPresentKey, data, Collections.emptyList());
        firstDate = dateTimeExtractor.runAdditionalValidator(firstDate, firstDate.getValidatedValue(), new RelativeDateTimesValidator(secondDate.getValidatedValue(), RelativeDateTimeComparator.EQUALS));
        assertTrue(firstDate.isValid());
    }
}
