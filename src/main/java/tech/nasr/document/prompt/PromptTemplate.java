package tech.nasr.document.prompt;

import java.util.Map;

public class PromptTemplate {

    private final String template;

    public PromptTemplate(String template) {
        this.template = template;
    }

    public String render(Map<String, String> variables){
        String result = template;

        for (Map.Entry<String, String> variable : variables.entrySet()) {

            result = result.replace(
                    "{{" + variable.getKey() + "}}",
                    variable.getValue());

        }

        return result;
    }

}