package com.example.rostorante.controllers;

import com.example.rostorante.model.Cardapio;
import com.example.rostorante.repository.CardapioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static com.example.rostorante.utils.TestUtils.logErrors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardapioControllerTest {

    @InjectMocks
    private CardapioController cardapioController;

    @Mock
    private CardapioRepository cardapioRepository;

    private Cardapio cardapio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cardapio = new Cardapio();
        cardapio.setId(1L);
        cardapio.setNome("Cardapio Teste");
    }

    @Test
    void listarTodos() {
        when(cardapioRepository.findAll()).thenReturn(Collections.singletonList(cardapio));

        List<Cardapio> result = cardapioController.listarTodos();
        assertEquals(1, result.size());
        String expectation = "Cardapio Teste";
        String actualResult = result.get(0).getNome();
        logErrors(actualResult, expectation);
        assertEquals(expectation, actualResult);
    }



    @Test
    void buscarPorId() {
        when(cardapioRepository.findById(1L)).thenReturn(Optional.of(cardapio));

        ResponseEntity<Cardapio> result = cardapioController.buscarPorId(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Cardapio Teste", Objects.requireNonNull(result.getBody()).getNome());
    }

    @Test
    void criarCardapio() {
        when(cardapioRepository.save(cardapio)).thenReturn(cardapio);

        Cardapio result = cardapioController.criarCardapio(cardapio);
        assertEquals("Cardapio Teste", result.getNome());
    }

    @Test
    void atualizarCardapio() {
        when(cardapioRepository.findById(1L)).thenReturn(Optional.of(cardapio));
        when(cardapioRepository.save(cardapio)).thenReturn(cardapio);

        ResponseEntity<Cardapio> result = cardapioController.atualizarCardapio(1L, cardapio);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Cardapio Teste", Objects.requireNonNull(result.getBody()).getNome());
    }

    @Test
    void deletarCardapio() {
        when(cardapioRepository.findById(1L)).thenReturn(Optional.of(cardapio));
        doNothing().when(cardapioRepository).delete(cardapio);

        ResponseEntity<Object> result = cardapioController.deletarCardapio(1L);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}
