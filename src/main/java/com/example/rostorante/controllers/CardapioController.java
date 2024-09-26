package com.example.rostorante.controllers;

import com.example.rostorante.model.Cardapio;
import com.example.rostorante.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardapios")
public class CardapioController {

    @Autowired
    private CardapioRepository cardapioRepository;

    @GetMapping
    public List<Cardapio> listarTodos() {
        return cardapioRepository.findAll();
    }

    @PostMapping
    public Cardapio criarCardapio(@RequestBody Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> buscarPorId(@PathVariable Long id) {
        return cardapioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cardapio> atualizarCardapio(@PathVariable Long id, @RequestBody Cardapio cardapio) {
        return cardapioRepository.findById(id)
                .map(cardapioExistente -> {
                    cardapio.setId(id);
                    Cardapio atualizado = cardapioRepository.save(cardapio);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCardapio(@PathVariable Long id) {
        return cardapioRepository.findById(id)
                .map(cardapio -> {
                    cardapioRepository.delete(cardapio);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
