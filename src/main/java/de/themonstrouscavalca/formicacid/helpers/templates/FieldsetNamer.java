package de.themonstrouscavalca.formicacid.helpers.templates;

public class FieldsetNamer{
    public static String id(String fieldsetName, String formName){
        if(formName != null && !formName.isEmpty()){
            return String.format("%s_fieldset_%s", formName, fieldsetName);
        }else{
            return String.format("fieldset_%s",  fieldsetName);
        }
    }
}
