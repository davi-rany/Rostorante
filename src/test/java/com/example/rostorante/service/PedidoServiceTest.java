package com.example.rostorante.service;

import com.example.rostorante.model.Pedido;
import com.example.rostorante.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoNotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarPedido_deveSalvarPedidoComStatusPendente() {
        Pedido pedido = new Pedido();
        pedido.setDescricao("Pizza Margherita");
        pedido.setQuantidade(2);

        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido novoPedido = pedidoService.criarPedido(pedido);

        assertNotNull(novoPedido);
        assertEquals("pendente", novoPedido.getStatus());
        verify(notificationService, times(1)).notificarNovoPedido(pedido);
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void listarPedidosPendentes_deveRetornarPedidosComStatusPendente() {
        Pedido pedido1 = new Pedido();
        pedido1.setStatus("pendente");
        Pedido pedido2 = new Pedido();
        pedido2.setStatus("pendente");

        when(pedidoRepository.findByStatus("pendente")).thenReturn(Arrays.asList(pedido1, pedido2));

        List<Pedido> pedidosPendentes = pedidoService.listarPedidosPendentes();

        assertEquals(2, pedidosPendentes.size());
        verify(pedidoRepository, times(1)).findByStatus("pendente");
    }

    @Test
    void atualizarStatus_deveAtualizarStatusDoPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus("pendente");

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido pedidoAtualizado = pedidoService.atualizarStatus(1L, "preparando");

        assertEquals("preparando", pedidoAtualizado.getStatus());
        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void listarPedidosConcluidos_deveRetornarPedidosComStatusConcluido() {
        Pedido pedido = new Pedido();
        pedido.setStatus("concluido");

        when(pedidoRepository.findByStatus("concluido")).thenReturn(Arrays.asList(pedido));

        List<Pedido> pedidosConcluidos = pedidoService.listarPedidosConcluidos();

        assertEquals(1, pedidosConcluidos.size());
        verify(pedidoRepository, times(1)).findByStatus("concluido");
    }

    @Test
    void listarPedidosEmPreparo_deveRetornarPedidosComStatusPreparando() {
        Pedido pedido = new Pedido();
        pedido.setStatus("preparando");

        when(pedidoRepository.findByStatus("preparando")).thenReturn(Arrays.asList(pedido));

        List<Pedido> pedidosEmPreparo = pedidoService.listarPedidosEmPreparo();

        assertEquals(1, pedidosEmPreparo.size());
        verify(pedidoRepository, times(1)).findByStatus("preparando");
    }
}
