package br.infnet.ATJava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VoiceActor {
    @JsonProperty("English")
    private String English;
    @JsonProperty("Chinese")
    private String Chinese;
    @JsonProperty("Japanese")
    private String Japanese;
    @JsonProperty("Korean")
    private String Korean;
}

