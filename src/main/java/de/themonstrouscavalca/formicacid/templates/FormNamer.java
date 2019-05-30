package de.themonstrouscavalca.formicacid.templates;

public class FormNamer{
    public static String id(String formName){
        if(formName != null && !formName.isEmpty()){
            return String.format("form_%s", formName);
        }
        return "";
    }
}
