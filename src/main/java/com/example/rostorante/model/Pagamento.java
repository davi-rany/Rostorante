package com.example.rostorante.model;

import com.example.rostorante.enums.MetodoPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O valor do pagamento é obrigatório")
    @Positive(message = "O valor do pagamento deve ser positivo")
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O método de pagamento é obrigatório")
    private MetodoPagamento metodo;

    private LocalDateTime dataPagamento;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
