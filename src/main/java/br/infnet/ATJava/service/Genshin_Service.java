package br.infnet.ATJava.service;

import br.infnet.ATJava.model.GenshinResultDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Genshin_Service {
    // Simulando um banco de dados em memória para armazenar os personagens
    private Map<Integer, GenshinResultDTO> personagens;

    public Genshin_Service() {
        this.personagens = new HashMap<>();
        // Adicionando alguns personagens de exemplo
        adicionarExemplos();
    }

    // Método para adicionar personagens de exemplo ao iniciar o serviço
    private void adicionarExemplos() {
        GenshinResultDTO personagem1 = new GenshinResultDTO();
        personagem1.setId(1);
        personagem1.setName("Exemplo1");
        // Define outros atributos...

        GenshinResultDTO personagem2 = new GenshinResultDTO();
        personagem2.setId(2);
        personagem2.setName("Exemplo2");
        // Define outros atributos...

        personagens.put(1, personagem1);
        personagens.put(2, personagem2);
    }

    // Método para salvar um novo personagem
    public GenshinResultDTO salvarPersonagem(GenshinResultDTO novoPersonagem) {
        int novoId = personagens.size() + 1; // Gerando um novo ID (simulação)
        novoPersonagem.setId(novoId);
        personagens.put(novoId, novoPersonagem);
        return novoPersonagem;
    }

    // Método para atualizar um personagem existente
    public void atualizarPersonagem(int id, GenshinResultDTO personagemAtualizado) {
        if (personagens.containsKey(id)) {
            personagemAtualizado.setId(id);
            personagens.put(id, personagemAtualizado);
        } else {
            throw new IllegalArgumentException("Personagem não encontrado para o ID fornecido: " + id);
        }
    }

    // Método para deletar um personagem existente
    public void deletarPersonagem(int id) {
        if (personagens.containsKey(id)) {
            personagens.remove(id);
        } else {
            throw new IllegalArgumentException("Personagem não encontrado para o ID fornecido: " + id);
        }
    }

    // Método para obter um personagem pelo ID
    public GenshinResultDTO obterPorId(int id) {
        return personagens.getOrDefault(id, null);
    }

    // Método para obter todos os personagens
    public List<GenshinResultDTO> listarTodos() {
        return new ArrayList<>(personagens.values());
    }
}
