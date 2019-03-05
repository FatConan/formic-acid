package de.themonstrouscavalca.formicacid.tests.validation;

import de.themonstrouscavalca.formicacid.extractors.impl.basic.*;
import de.themonstrouscavalca.formicacid.extractors.impl.collection.*;

public class ValidationBase{
    protected final StringExtractor stringExtractor = new StringExtractor();
    protected final LongExtractor longExtractor = new LongExtractor();
    protected final DoubleExtractor doubleExtractor = new DoubleExtractor();
    protected final IntegerExtractor integerExtractor = new IntegerExtractor();
    protected final BooleanExtractor booleanExtractor = new BooleanExtractor();
    protected final LocalDateTimeExtractor dateTimeExtractor = new LocalDateTimeExtractor();
    protected final LocalDateExtractor dateExtractor = new LocalDateExtractor();
    protected final LocalTimeExtractor timeExtractor = new LocalTimeExtractor();

    protected final StringListExtractor stringListExtractor = new StringListExtractor();
    protected final LongListExtractor longListExtractor = new LongListExtractor();
    protected final DoubleListExtractor doubleListExtractor = new DoubleListExtractor();
    protected final IntegerListExtractor integerListExtractor = new IntegerListExtractor();
    protected final BooleanListExtractor booleanListExtractor = new BooleanListExtractor();
    protected final LocalDateTimeListExtractor dateTimeListExtractor = new LocalDateTimeListExtractor();
    protected final LocalDateListExtractor dateListExtractor = new LocalDateListExtractor();
    protected final LocalTimeListExtractor timeListExtractor = new LocalTimeListExtractor();
}
