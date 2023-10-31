package de.themonstrouscavalca.formicacid.tests.abs;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.abs.AbstractSimpleMarshaller;
import de.themonstrouscavalca.formicacid.difference.DifferenceProcessor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.LongExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.StringExtractor;
import de.themonstrouscavalca.formicacid.validators.impl.basic.RequiredValidator;
import de.themonstrouscavalca.formicacid.validators.impl.strings.StringLengthValidator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class AbstractSimpleMarshallerTest{
    public static class Book{
        public String title;
        public String author;
        public Long ID;
    }

    public static class BookMarshaller extends AbstractSimpleMarshaller<Book>{
        private final ObjectMapper mapper = new ObjectMapper();
        private final StringExtractor stringExtractor = new StringExtractor();
        private final LongExtractor longExtractor = new LongExtractor();


        @Override
        public void validationSteps(Book entity, JsonNode json){
            this.validateValue(entity, "id", json, this.longExtractor,
                    requiredOnly(),
                    (v, e) -> e.ID = v.get());
            this.validateValue(entity, "title", json, this.stringExtractor,
                    Arrays.asList(new RequiredValidator<>(), new StringLengthValidator(1, 255)),
                    (v, e) -> e.title = v.get());
            this.validateValue(entity, "author", json, this.stringExtractor,
                    Arrays.asList(new RequiredValidator<>(), new StringLengthValidator(1, 255)),
                    (v, e) -> e.author = v.get());
        }

        @Override
        public JsonNode toJson(Book entity){
            ObjectNode bookRepr = this.mapper.createObjectNode();
            bookRepr.put("id", entity.ID);
            bookRepr.put("title", entity.title);
            bookRepr.put("author", entity.author);
            return bookRepr;
        }

        @Override
        public Book create(){
            return new Book();
        }
    }

    @Test
    public void simpleMarshallerSuccess(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode bookRepr = mapper.createObjectNode();
        bookRepr.put("id", 1L);
        bookRepr.put("title", "The Formic of Acid");
        bookRepr.put("author", "Fat Conan");

        BookMarshaller marshaller = new BookMarshaller();
        Optional<Book> book = marshaller.validate(bookRepr);
        assert(book.isPresent());
        Optional<JsonNode> repr = book.map(marshaller::toJson);
        assert(repr.isPresent());
        assert(DifferenceProcessor.match(bookRepr, repr.get()));
    }

    @Test
    public void simpleMarshallerFailure(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode bookRepr = mapper.createObjectNode();
        bookRepr.put("id", 1L);
        bookRepr.put("title", "The Formic of Acid");
        bookRepr.put("authored", "Fat Conan");

        BookMarshaller marshaller = new BookMarshaller();
        Optional<Book> book = marshaller.validate(bookRepr);
        assert(!book.isPresent());
        assert(marshaller.hasErrors());
        assert(marshaller.getErrors().hasFieldError("author"));
    }
}