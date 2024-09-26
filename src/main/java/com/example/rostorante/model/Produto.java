package com.example.rostorante.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    private String descricao;

    @Positive(message = "O preço deve ser positivo")
    private BigDecimal preco;

    @NotNull(message = "A categoria do produto é obrigatória")
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    public enum CategoriaProduto {
        BEBIDA, PRATO_PRINCIPAL, SOBREMESA, ENTRADA
    }
}
