package com.example.rostorante.service;

import com.example.rostorante.enums.PedidoStatusEnum;
import com.example.rostorante.model.Pedido;
import com.example.rostorante.repository.PedidoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoNotificationService notificationService;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(PedidoStatusEnum.PENDENTE);
        Pedido novoPedido = pedidoRepository.save(pedido);
        notificationService.notificarNovoPedido(novoPedido);
        return novoPedido;
    }

    public List<Pedido> listarPedidosPendentes() {
        return pedidoRepository.findByStatus(PedidoStatusEnum.PENDENTE);
    }

    public List<Pedido> listarPedidosConcluidos() {
        return pedidoRepository.findByStatus(PedidoStatusEnum.CONCLUIDO);
    }

    public List<Pedido> listarPedidosEmPreparo() {
        return pedidoRepository.findByStatus(PedidoStatusEnum.EM_PREPARO);
    }

    public List<Pedido> listarPedidosCancelados() {
        return pedidoRepository.findByStatus(PedidoStatusEnum.CANCELADO);
    }

    public List<Pedido> listarPedidosPorMesa(Integer mesa) {
        return pedidoRepository.findByMesa(mesa);
    }

    public Pedido atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        pedido.setStatus(PedidoStatusEnum.valueOf(novoStatus));
        return pedidoRepository.save(pedido);
    }
}

