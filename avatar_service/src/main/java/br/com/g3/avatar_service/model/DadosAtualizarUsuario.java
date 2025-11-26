package br.com.g3.avatar_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarUsuario(
        @NotNull
        Long id,
        String nome,
        String email
) {
}
