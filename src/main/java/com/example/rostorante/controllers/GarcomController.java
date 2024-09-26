package com.example.rostorante.controllers;

import com.example.rostorante.model.Garcom;
import com.example.rostorante.repository.GarcomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garcons")
public class GarcomController {

    @Autowired
    private GarcomRepository garcomRepository;

    @GetMapping
    public List<Garcom> listarTodos() {
        return garcomRepository.findAll();
    }

    @PostMapping
    public Garcom criarGarcom(@RequestBody Garcom garcom) {
        return garcomRepository.save(garcom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garcom> buscarPorId(@PathVariable Long id) {
        return garcomRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Garcom> atualizarGarcom(@PathVariable Long id, @RequestBody Garcom garcom) {
        return garcomRepository.findById(id)
                .map(garcomExistente -> {
                    garcom.setId(id);
                    Garcom atualizado = garcomRepository.save(garcom);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarGarcom(@PathVariable Long id) {
        return garcomRepository.findById(id)
                .map(garcom -> {
                    garcomRepository.delete(garcom);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
