package br.infnet.ATJava.controller;

import br.infnet.ATJava.model.GenshinResultDTO;
import br.infnet.ATJava.service.Genshin_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta-genshin/characters")
public class GenshinController {

    @Autowired
    private Genshin_Service genshinService;

    @GetMapping("/{id}")
    public ResponseEntity<GenshinResultDTO> consultarPersonagem(@PathVariable("id") int id) {
        GenshinResultDTO result = genshinService.obterPorId(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GenshinResultDTO>> listarPersonagens() {
        List<GenshinResultDTO> personagens = genshinService.listarTodos();
        if (!personagens.isEmpty()) {
            return ResponseEntity.ok(personagens);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrarPersonagem(@RequestBody GenshinResultDTO genshinResultDTO) {
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
        try {
            genshinService.deletarPersonagem(id);
            return ResponseEntity.ok("Personagem deletado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o personagem.");
        }
    }
}
