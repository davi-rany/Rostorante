package com.example.rostorante.model;

import com.example.rostorante.enums.PedidoStatusEnum;
import com.example.rostorante.utils.StringListConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A descrição do pedido é obrigatória")
    @Size(max = 255, message = "A descrição não pode ultrapassar 255 caracteres")
    private String descricao;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade mínima é 1")
    private Integer quantidade;

    @NotNull(message = "O status do pedido é obrigatório")
    @Enumerated(EnumType.STRING)
    private PedidoStatusEnum status;

    @NotNull(message = "A mesa associada ao pedido é obrigatória")
    @Min(value = 1, message = "A mesa deve ser um número válido")
    private Integer mesa;

    @Convert(converter = StringListConverter.class)
    @NotNull(message = "A lista de itens não pode ser nula")
    @Size(min = 1, message = "O pedido deve conter pelo menos 1 item")
    private List<String> itens;

    @ManyToOne
    @JoinColumn(name = "garcom_id")
    private Garcom garcom;
}
