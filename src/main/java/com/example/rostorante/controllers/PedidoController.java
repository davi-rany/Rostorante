package com.example.rostorante.controllers;

import com.example.rostorante.model.Pedido;
import com.example.rostorante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Pedido>> listarPedidosPendentes() {
        List<Pedido> pedidos = pedidoService.listarPedidosPendentes();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/concluidos")
    public ResponseEntity<List<Pedido>> listarPedidosCondluidos() {
        List<Pedido> pedidos = pedidoService.listarPedidosConcluidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/em-preparo")
    public ResponseEntity<List<Pedido>> listarPedidosEmPreparo() {
        List<Pedido> pedidos = pedidoService.listarPedidosEmPreparo();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/mesa{numero}")
    public ResponseEntity<List<Pedido>> listarPedidosPorMesa(@PathVariable Integer numero) {
        List<Pedido> pedidos = pedidoService.listarPedidosPorMesa(numero);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        Pedido pedidoAtualizado = pedidoService.atualizarStatus(id, status);
        return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
    }
}