package br.infnet.ATJava.controller;

import br.infnet.ATJava.model.GenshinResponseDTO;
import br.infnet.ATJava.model.GenshinResultDTO;
import br.infnet.ATJava.service.Genshin_Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/consulta-genshin/characters")
public class GenshinAPI {

    @Autowired
    private Genshin_Service genshinService; // Supondo que você tenha um serviço para processamento



    @GetMapping("/{id}")
    public ResponseEntity<GenshinResultDTO> consultarPersonagem(@PathVariable("id") int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://gsi.fly.dev/characters/" + id, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String jsonString = responseEntity.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                GenshinResponseDTO response = objectMapper.readValue(jsonString, GenshinResponseDTO.class);
                GenshinResultDTO result = response.getResult();
                return ResponseEntity.ok(result);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrarPersonagem(@RequestBody GenshinResultDTO genshinResultDTO) {
        // Implementação para cadastrar um novo personagem
        // Supondo que a lógica para salvar um novo personagem esteja no serviço Genshin_Service
        try {
            GenshinResultDTO novoPersonagem = genshinService.salvarPersonagem(genshinResultDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Personagem cadastrado com sucesso: " + novoPersonagem.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o personagem.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPersonagem(@PathVariable("id") int id, @RequestBody GenshinResultDTO genshinResultDTO) {
        // Implementação para atualizar um personagem existente por ID
        // Supondo que a lógica para atualizar um personagem esteja no serviço Genshin_Service
        try {
            genshinService.atualizarPersonagem(id, genshinResultDTO);
            return ResponseEntity.ok("Personagem atualizado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o personagem.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPersonagem(@PathVariable("id") int id) {
        // Implementação para deletar um personagem por ID
        // Supondo que a lógica para deletar um personagem esteja no serviço Genshin_Service
        try {
            genshinService.deletarPersonagem(id);
            return ResponseEntity.ok("Personagem deletado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o personagem.");
        }
    }
}
//TESTE PARA COMIT
