package de.themonstrouscavalca.formicacid.helpers.dynamic;

public enum DynamicExtractorType{
    STRING("STRING"),
    LONG("LONG");

    private final String name;

    DynamicExtractorType(String name){
        this.name = name;
    }

    public static DynamicExtractorType fromName(String name){
        for(DynamicExtractorType p: values()){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
