package com.example.rostorante.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Garcom extends Usuario {

    @ElementCollection
    @CollectionTable(name = "mesas_atendidas", joinColumns = @JoinColumn(name = "garcom_id"))
    @Column(name = "mesa_numero")
    private List<Integer> mesas;

    @Positive(message = "A gorjeta deve ser um valor positivo")
    private BigDecimal gorjetas;

    @NotNull(message = "A data de contratação é obrigatória")
    private LocalDate dataContratacao;

    @NotBlank(message = "O turno é obrigatório")
    private String turno;

    @NotNull(message = "O status é obrigatório")
    private Boolean status;

    @Positive(message = "O salário deve ser um valor positivo")
    private BigDecimal salario;

    @Positive(message = "A avaliação deve ser um valor positivo")
    private Double avaliacao;

    @OneToMany(mappedBy = "garcom")
    private List<Pedido> pedidos;
}
