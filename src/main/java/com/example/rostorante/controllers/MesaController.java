package com.example.rostorante.controllers;

import com.example.rostorante.model.Mesa;
import com.example.rostorante.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping
    public List<Mesa> listarTodas() {
        return mesaRepository.findAll();
    }

    @PostMapping
    public Mesa criarMesa(@RequestBody Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscarPorId(@PathVariable Long id) {
        return mesaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> atualizarMesa(@PathVariable Long id, @RequestBody Mesa mesa) {
        return mesaRepository.findById(id)
                .map(mesaExistente -> {
                    mesa.setId(id);
                    Mesa atualizada = mesaRepository.save(mesa);
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarMesa(@PathVariable Long id) {
        return mesaRepository.findById(id)
                .map(mesa -> {
                    mesaRepository.delete(mesa);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
