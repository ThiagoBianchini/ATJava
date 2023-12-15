package br.infnet.ATJava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@Data
public class GenshinResultDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("title")
    private List<String> title;
    public GenshinResultDTO(int id, String name, List<String> title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }
}



