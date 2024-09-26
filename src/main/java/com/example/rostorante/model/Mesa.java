package com.example.rostorante.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    @Enumerated(EnumType.STRING)
    private StatusMesa status;

    public enum StatusMesa {
        OCUPADA, LIVRE, RESERVADA
    }

    @OneToMany(mappedBy = "mesa")
    private List<Pedido> pedidos;
}
