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
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class UpdatedPasswordValidation{

    private static final Collection<IValidate<String>> PASSWORD_VALIDATORS = Arrays.asList(
            new RequiredValidator<>("REQUIRED"),
            new StringLengthValidator(6, null, "LENGTH" ,"LENGTH", "LENGTH"),
            new PasswordStringValidator("POLICY")
    );

    public static class Password{
        final ValidatedOptional<String> initialPassword;
        final ValidatedOptional<String> confirmPassword;

        Password(ValidatedOptional<String> initialPassword, ValidatedOptional<String> confirmPassword){
            this.initialPassword = initialPassword;
            this.confirmPassword = confirmPassword;
        }
    }

    private Password validatedPassword(JsonNode json, Collection<String> checkErrors, boolean expectedNull){
        ValidatedOptional<String> passwordInitial = ValidatedOptional.empty();
        ValidatedOptional<String> passwordConfirmation = ValidatedOptional.empty();
        StringExtractor stringExtractor = new StringExtractor();
        Password password = null;

        if(json != null) {
            passwordConfirmation = stringExtractor.extractValidatedValue("password_confirm", json, PASSWORD_VALIDATORS);
            IntermediateValidateOptional<String> passwordInitialInter = stringExtractor.extractFinalIntermediate(
                    "password_initial", json, PASSWORD_VALIDATORS);
            passwordInitial = new ValidatedOptional<>(passwordInitialInter);

            if(passwordInitialInter.isValid() && passwordConfirmation.isValid()){
                passwordInitialInter = stringExtractor.runAdditionalValidator(passwordInitialInter, passwordInitialInter.getValidatedValue(),
                        new StringMatchValidator(passwordConfirmation.getValidatedValue(), "MATCH"));
                passwordInitial = new ValidatedOptional<>(passwordInitialInter);
            }

            List<String> errors = Arrays.asList(passwordInitial.getErrors());
            assert(errors.containsAll(checkErrors));

            if(passwordInitial.isValid() && passwordConfirmation.isValid()){
                password = new Password(passwordInitial, passwordConfirmation);
            }
        }

        if(expectedNull){
            assertNull(password);
        }else{
            assertNotNull(password);
        }

        return password;
    }

    @Test
    public void validatePasswordDuo(){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("password_initial", "");
        node.put("password_confirm", "password");

        Password password = validatedPassword(node, Collections.singletonList("REQUIRED"), true);
        assertNull(password);

        node = JsonNodeFactory.instance.objectNode();
        node.put("password_initial", "password");
        node.put("password_confirm", "password");

        password = validatedPassword(node, Collections.singletonList("POLICY"), true);
        assertNull(password);

        node = JsonNodeFactory.instance.objectNode();
        node.put("password_initial", "pass");
        node.put("password_confirm", "pass");

        password = validatedPassword(node, Arrays.asList("LENGTH", "POLICY"), true);
        assertNull(password);

        node = JsonNodeFactory.instance.objectNode();
        node.put("password_initial", "passwordMismatch123");
        node.put("password_confirm", "passwordMatchmis123");

        password = validatedPassword(node, Collections.singletonList("MATCH"), true);
        assertNull(password);

        node = JsonNodeFactory.instance.objectNode();
        node.put("password_initial", "passWORD123");
        node.put("password_confirm", "passWORD123");

        password = validatedPassword(node, Collections.emptyList(), false);
        assertNotNull(password);
    }
}
