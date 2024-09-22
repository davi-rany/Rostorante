package com.example.rostorante.service;

import com.example.rostorante.model.Pedido;
import com.example.rostorante.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoNotificationService notificationService;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus("pendente");
        Pedido novoPedido = pedidoRepository.save(pedido);
        notificationService.notificarNovoPedido(novoPedido);
        return novoPedido;
    }

    public List<Pedido> listarPedidosPendentes() {
        return pedidoRepository.findByStatus("pendente");
    }

    public List<Pedido> listarPedidosConcluidos() {
        return pedidoRepository.findByStatus("concluido");
    }

    public List<Pedido> listarPedidosEmPreparo() {
        return pedidoRepository.findByStatus("preparando");
    }

    public Pedido atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }
}

