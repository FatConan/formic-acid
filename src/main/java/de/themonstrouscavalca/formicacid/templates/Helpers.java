package de.themonstrouscavalca.formicacid.templates;

import play.twirl.api.Html;

public class Helpers{
    public static Html reduce(Html content){
        String contentBody = content.body().trim();
        contentBody = contentBody.replaceAll("\\n", "")
                .replaceAll("\\s+", " ")
                .replaceAll(">\\s+<", "><")
                .replaceAll("\\s+>", ">")
                .replaceAll("<\\s+", "<");

        return new Html(contentBody);
    }
}
