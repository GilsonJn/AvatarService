package br.com.g3.avatar_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.beans.XMLEncoder;

public record DadosCadastroUsuario(
        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "Formato do email inválido.")
        String email
) {
}
