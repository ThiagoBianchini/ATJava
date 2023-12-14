package br.infnet.ATJava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenshinResponseDTO {
    @JsonProperty("result")
    private GenshinResultDTO result;
}
