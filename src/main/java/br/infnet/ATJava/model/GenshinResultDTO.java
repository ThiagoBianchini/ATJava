package br.infnet.ATJava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GenshinResultDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("title")
    private List<String> title;
    @JsonProperty("real_name")
    private String real_name;
    @JsonProperty("rarity")
    private String rarity;
    @JsonProperty("weapon")
    private String weapon;
    @JsonProperty("vision")
    private String vision;
    @JsonProperty("model_type")
    private String model_type;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("constellation")
    private String constellation;
    @JsonProperty("region")
    private List<String> region;
    @JsonProperty("affiliation")
    private List<String> affiliation;
    @JsonProperty("special_dish")
    private String special_dish;
    @JsonProperty("how_to_obtain")
    private List<String> how_to_obtain;
    @JsonProperty("release_day")
    private String release_day;
    @JsonProperty("release_version")
    private String release_version;
    @JsonProperty("category")
    private String category;
    @JsonProperty("voice_actors")
    private List<VoiceActor> voice_actors;
    @JsonProperty("wiki_url")
    private String wiki_url;
}



