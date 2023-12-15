package br.infnet.ATJava.service;

import br.infnet.ATJava.model.GenshinResultDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class Genshin_Service {
    private List<GenshinResultDTO> personagens;

    public Genshin_Service() {
        this.personagens = new ArrayList<>();
        adicionarPersonagens();
    }

    private void adicionarPersonagens() {
        personagens.add(new GenshinResultDTO(1, "Amber", Arrays.asList("Gliding Champion", "Outrider")));
        personagens.add(new GenshinResultDTO(2, "Barbara", Arrays.asList("Shining Idol", "Deaconess")));
        personagens.add(new GenshinResultDTO(3, "Beidou", Arrays.asList("Uncrowned Lord of the Ocean", "Queen of the Crux Fleet")));
        personagens.add(new GenshinResultDTO(4, "Bennett", Arrays.asList("Trial by Fire", "Leader of Benny's Adventure Team")));
        personagens.add(new GenshinResultDTO(5, "Chongyun", Arrays.asList("Frozen Ardor", "Banisher of Evil and Rumors Thereof")));
        personagens.add(new GenshinResultDTO(6, "Fischl", Arrays.asList("Prinzessin der Verurteilung!", "Sovereign of Immernachtreich")));
        personagens.add(new GenshinResultDTO(7, "Kaeya", Arrays.asList("Frostwind Swordsman", "Quartermaster of the Knights")));
    }

    public GenshinResultDTO salvarPersonagem(GenshinResultDTO novoPersonagem) {
        int novoId = personagens.size() + 1;
        novoPersonagem.setId(novoId);
        personagens.add(novoPersonagem);
        return novoPersonagem;
    }

    public void atualizarPersonagem(int id, GenshinResultDTO personagemAtualizado) {
        boolean atualizado = false;
        for (GenshinResultDTO personagem : personagens) {
            if (personagem.getId() == id) {
                personagemAtualizado.setId(id);
                personagens.set(id - 1, personagemAtualizado);
                atualizado = true;
                break;
            }
        }
        if (!atualizado) {
            throw new IllegalArgumentException("Personagem não encontrado para o ID fornecido: " + id);
        }
    }

    public void deletarPersonagem(int id) {
        boolean removido = personagens.removeIf(p -> p.getId() == id);
        if (!removido) {
            throw new IllegalArgumentException("Personagem não encontrado para o ID fornecido: " + id);
        }
    }

    public GenshinResultDTO obterPorId(int id) {
        for (GenshinResultDTO personagem : personagens) {
            if (personagem.getId() == id) {
                return personagem;
            }
        }
        return null;
    }

    public List<GenshinResultDTO> listarTodos() {
        return new ArrayList<>(personagens);
    }
}
