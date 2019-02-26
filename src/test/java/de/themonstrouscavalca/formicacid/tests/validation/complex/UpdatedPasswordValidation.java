package de.themonstrouscavalca.formicacid.tests.validation.complex;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.StringExtractor;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;
import de.themonstrouscavalca.formicacid.validators.impl.basic.RequiredValidator;
import de.themonstrouscavalca.formicacid.validators.impl.strings.PasswordStringValidator;
import de.themonstrouscavalca.formicacid.validators.impl.strings.StringLengthValidator;
import de.themonstrouscavalca.formicacid.validators.impl.strings.StringMatchValidator;
import org.junit.Test;

import javax.xml.bind.ValidationEvent;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class UpdatedPasswordValidation{

    private static final Collection<IValidate<String>> PASSWORD_VALIDATORS = Arrays.asList(
            new RequiredValidator<>(),
            new StringLengthValidator(6, null),
            new PasswordStringValidator()
    );

    public static class Password{
        final ValidatedOptional<String> initialPassword;
        final ValidatedOptional<String> confirmPassword;

        Password(ValidatedOptional<String> initialPassword, ValidatedOptional<String> confirmPassword){
            this.initialPassword = initialPassword;
            this.confirmPassword = confirmPassword;
        }
    }

    private Password validatedPassword(JsonNode json){
        ValidatedOptional<String> passwordInitial = ValidatedOptional.empty();
        ValidatedOptional<String> passwordConfirmation = ValidatedOptional.empty();
        StringExtractor stringExtractor = new StringExtractor();

        if(json != null) {
            //passwordInitial = stringExtractor.extractValidatedValue("password_initial", json, PASSWORD_VALIDATORS);
            //assertFalse(passwordInitial.isValid());
            //assertNull(passwordInitial.get());

            passwordConfirmation = stringExtractor.extractValidatedValue("password_confirm", json, PASSWORD_VALIDATORS);
            //assertTrue(passwordConfirmation.isValid());
            //assertNotNull(passwordConfirmation.get());

            IntermediateValidateOptional<String> passwordInitialInter = stringExtractor.extractFinalIntermediate(
                    "password_initial", json, PASSWORD_VALIDATORS);
            //assertFalse(passwordInitialInter.isValid());
            //assertNull(passwordInitialInter.getValidatedValue());
            passwordInitial = new ValidatedOptional<>(passwordInitialInter);

            if(passwordInitialInter.isValid() && passwordConfirmation.isValid()){
                passwordInitialInter = stringExtractor.runAdditionalValidator(passwordInitialInter, passwordInitialInter.getValidatedValue(),
                        new StringMatchValidator(passwordConfirmation.getValidatedValue()));
                passwordInitial = new ValidatedOptional<>(passwordInitialInter);
            }

            if(passwordInitial.isValid() && passwordConfirmation.isValid()){
                return new Password(passwordInitial, passwordConfirmation);
            }
        }


        return null;
    }

    @Test
    public void validatePasswordDuo(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("password_initial", "");
        node.put("password_confirm", "password");

        Password password = validatedPassword(node);
        assertNull(password);

        node = JsonNodeFactory.instance.objectNode();
        node.put("password_initial", "passwordMismatch");
        node.put("password_confirm", "password");

        password = validatedPassword(node);
        assertNull(password);

        node = JsonNodeFactory.instance.objectNode();
        node.put("password_initial", "pass");
        node.put("password_confirm", "pass");

        password = validatedPassword(node);
        assertNull(password);

        node = JsonNodeFactory.instance.objectNode();
        node.put("password_initial", "password");
        node.put("password_confirm", "password");

        password = validatedPassword(node);
        assertNotNull(password);

    }
}
