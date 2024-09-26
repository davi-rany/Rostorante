package com.example.rostorante.repository;

import com.example.rostorante.enums.PedidoStatusEnum;
import com.example.rostorante.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(PedidoStatusEnum status);
    List<Pedido> findByMesa(Integer mesa);
}

