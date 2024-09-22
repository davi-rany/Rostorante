package com.example.rostorante.service;

import com.example.rostorante.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public PedidoNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notificarNovoPedido(Pedido pedido) {
        messagingTemplate.convertAndSend("/fila/novos-pedidos", pedido);
    }
}

