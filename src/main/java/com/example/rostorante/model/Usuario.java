package com.example.rostorante.model;

import com.example.rostorante.enums.NivelDeAcessoUsuarioEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "O e-mail deve ser válido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos")
    private String cpf;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve estar no formato (XX) XXXXX-XXXX")
    private String telefone;

    private String endereco;

    @Enumerated(EnumType.STRING)
    private NivelDeAcessoUsuarioEnum nivelDeAcesso;
}
