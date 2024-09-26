package com.example.rostorante.controllers;

import com.example.rostorante.model.Pagamento;
import com.example.rostorante.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping
    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    @PostMapping
    public Pagamento criarPagamento(@RequestBody Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Integer id) {
        return pagamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Integer id, @RequestBody Pagamento pagamento) {
        return pagamentoRepository.findById(id)
                .map(pagamentoExistente -> {
                    pagamento.setId(id);
                    Pagamento atualizado = pagamentoRepository.save(pagamento);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPagamento(@PathVariable Integer id) {
        return pagamentoRepository.findById(id)
                .map(pagamento -> {
                    pagamentoRepository.delete(pagamento);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
