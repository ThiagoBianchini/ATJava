package br.infnet.ATJava;

import br.infnet.ATJava.model.GenshinResultDTO;
import br.infnet.ATJava.service.Genshin_Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class Genshin_ServiceTests {

    private Genshin_Service genshinService;

    @BeforeEach
    void setUp() {
        genshinService = new Genshin_Service();
    }

    @Test
    void testSalvarPersonagem() {
        GenshinResultDTO novoPersonagem = new GenshinResultDTO(8, "New Character", List.of("Title1", "Title2"));
        GenshinResultDTO savedCharacter = genshinService.salvarPersonagem(novoPersonagem);

        Assertions.assertEquals(8, savedCharacter.getId());
        Assertions.assertEquals("New Character", savedCharacter.getName());
        Assertions.assertEquals(List.of("Title1", "Title2"), savedCharacter.getTitle());
    }

    @Test
    void testAtualizarPersonagem() {
        GenshinResultDTO personagemExistente = new GenshinResultDTO(9, "Existing Character", List.of("Title1", "Title2"));
        genshinService.salvarPersonagem(personagemExistente);

        GenshinResultDTO personagemAtualizado = new GenshinResultDTO(9, "Updated Character", List.of("Title3", "Title4"));
        genshinService.atualizarPersonagem(9, personagemAtualizado);

        GenshinResultDTO updatedCharacter = genshinService.obterPorId(9);
        Assertions.assertEquals("Updated Character", updatedCharacter.getName());
        Assertions.assertEquals(List.of("Title3", "Title4"), updatedCharacter.getTitle());
    }

    @Test
    void testDeletarPersonagem() {
        GenshinResultDTO personagemParaDeletar = new GenshinResultDTO(10, "Character to Delete", List.of("Title1", "Title2"));
        genshinService.salvarPersonagem(personagemParaDeletar);

        genshinService.deletarPersonagem(10);

        Assertions.assertNull(genshinService.obterPorId(10));
    }

    @Test
    void testObterPorId() {
        GenshinResultDTO personagemExistente = new GenshinResultDTO(11, "Existing Character", List.of("Title1", "Title2"));
        genshinService.salvarPersonagem(personagemExistente);

        GenshinResultDTO obtainedCharacter = genshinService.obterPorId(11);
        Assertions.assertEquals(11, obtainedCharacter.getId());
        Assertions.assertEquals("Existing Character", obtainedCharacter.getName());
        Assertions.assertEquals(List.of("Title1", "Title2"), obtainedCharacter.getTitle());
    }

    @Test
    void testObterPorIdNaoExistente() {
        Assertions.assertNull(genshinService.obterPorId(100));
    }

    @Test
    void testDeletarPersonagemNaoExistente() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> genshinService.deletarPersonagem(100));
    }

    @Test
    void testAtualizarPersonagemNaoExistente() {
        GenshinResultDTO personagemNaoExistente = new GenshinResultDTO(100, "Not Found", List.of("Title1", "Title2"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> genshinService.atualizarPersonagem(100, personagemNaoExistente));
    }
}
