package br.com.g3.avatar_service.model;

import java.time.LocalDate;

public record DadosResponseUsuario(
        Long id,
        String nome,
        String email,
        Boolean ativo,
        int pontosExperiencia,
        int nivel,
        LocalDate dataCadastro
) {
}
