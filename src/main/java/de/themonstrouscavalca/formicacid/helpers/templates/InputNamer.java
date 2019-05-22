package de.themonstrouscavalca.formicacid.helpers.templates;

public class InputNamer{
    public static String id(String inputName, String formName){
        if(formName != null && !formName.isEmpty()){
            return String.format("%s_input_%s", formName, inputName);
        }else{
            return String.format("input_%s",  inputName);
        }
    }
}
