package br.com.g3.avatar_service.controller;

import br.com.g3.avatar_service.model.DadosCadastroUsuario;
import br.com.g3.avatar_service.model.DadosResponseUsuario;
import br.com.g3.avatar_service.model.Usuario;
import br.com.g3.avatar_service.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // ENDPOINT DE PESQUISA DOS USUÁRIOS
    @GetMapping
    public ResponseEntity<List<DadosResponseUsuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    // ENDPOINT DE PESQUISA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<DadosResponseUsuario> getUsuarioById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    // ENDPOINT DE CADASTRO DE USUÁRIO
    @PostMapping
    public ResponseEntity<DadosResponseUsuario> createUsuario(@Valid @RequestBody DadosCadastroUsuario dados) {
        DadosResponseUsuario savedUsuario = usuarioService.save(dados);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    // ENDPOINT DE GAMIFICAÇÃO
    @PutMapping("/{id}/pontos")
    public ResponseEntity<DadosResponseUsuario> darPontosParaUsuario(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> body) {

        int pontosGanhos = body.getOrDefault("pontos", 0);

        DadosResponseUsuario usuarioAtualizado = usuarioService.adicionarPontos(id, pontosGanhos);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosResponseUsuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody DadosCadastroUsuario dados) {
        DadosResponseUsuario updateUsuario = usuarioService.updateUsuario(id, dados);
        return ResponseEntity.ok(updateUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
