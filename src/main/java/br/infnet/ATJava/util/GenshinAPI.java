package br.infnet.ATJava.util;

import br.infnet.ATJava.exception.ResourceNotFoundException;
import br.infnet.ATJava.model.GenshinResultDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Data;
import lombok.extern.java.Log;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Log
@Data
public class GenshinAPI {

    public static GenshinResultDTO getGenshinById(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("https://gshimpact.vercel.app/api/characters/" + id))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new ResourceNotFoundException("Personagem não encontrado");
            }

            ObjectMapper mapper = JsonMapper.builder().build();
            JsonNode jsonNode = mapper.readTree(response.body());

            int characterId = jsonNode.get("id").asInt();
            String characterName = jsonNode.get("name").asText();
            List<String> characterTitle = new ArrayList<>();
            JsonNode titleNode = jsonNode.get("title");
            if (titleNode != null && titleNode.isArray()) {
                for (JsonNode title : titleNode) {
                    characterTitle.add(title.asText());
                }
            }

            return new GenshinResultDTO(characterId, characterName, characterTitle);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
