package de.themonstrouscavalca.formicacid.errors;

public class ErrorNamespace{
    private static final ErrorNamespace DEFAULT = new ErrorNamespace("DEFAULT", true);
    public static ErrorNamespace getDefault(){
        return DEFAULT;
    }

    public static ErrorNamespace of(String nameSpace){
        return new ErrorNamespace(nameSpace);
    }

    private final String namespace;
    private final boolean defaultNameSpace;

    private ErrorNamespace(String nameSpace){
        this.namespace = nameSpace;
        this.defaultNameSpace = false;
    }

    private ErrorNamespace(String nameSpace, boolean defaultNameSpace){
        this.namespace = nameSpace;
        this.defaultNameSpace = defaultNameSpace;
    }

    public String getNamespace(){
        return namespace;
    }

    public boolean isDefaultNameSpace(){
        return defaultNameSpace;
    }
}
