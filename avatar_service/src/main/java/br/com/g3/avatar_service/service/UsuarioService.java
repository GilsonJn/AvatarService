package br.com.g3.avatar_service.service;

import br.com.g3.avatar_service.model.DadosCadastroUsuario;
import br.com.g3.avatar_service.model.DadosResponseUsuario;
import br.com.g3.avatar_service.model.Usuario;
import br.com.g3.avatar_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private DadosResponseUsuario toResponseUsuario(Usuario usuario) {
        return new DadosResponseUsuario(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.isAtivo(),
                usuario.getPontosExperiencia(),
                usuario.getNivel(),
                usuario.getDataCadastro()
        );
    }

    // PESQUISAR TODOS OS USUÁRIOS
    public List<DadosResponseUsuario> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this:: toResponseUsuario)
                .collect(Collectors.toList());
    }

    // PESQUISAR USUÁRIO POR ID
    public DadosResponseUsuario findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com esse id."));
        return toResponseUsuario(usuario);
    }

    // SALVAR USUÁRIO
    public DadosResponseUsuario save(DadosCadastroUsuario dados) {
        Usuario usuario = new Usuario();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());

        Usuario usuarioSaved = usuarioRepository.save(usuario);
        return toResponseUsuario(usuarioSaved);
    }

    // ATUALIZAR PONTOS
    public DadosResponseUsuario adicionarPontos(Long id, int pontosGanhos) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com esse id."));

        int pontosAtuais = usuario.getPontosExperiencia();
        int novosPontos = pontosAtuais + pontosGanhos;
        usuario.setPontosExperiencia(novosPontos);

        // Lógica para subir de nível a cada 100 pontos
        int novoNivel = (novosPontos / 100) + 1;
        usuario.setNivel(novoNivel);

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return toResponseUsuario(usuarioAtualizado);
    }

    // ATUALIZAR USUÁRIO
    public DadosResponseUsuario updateUsuario(Long id, DadosCadastroUsuario dados) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com esse id."));

        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return toResponseUsuario(usuarioAtualizado);
    }

    // "DELETAR" USUÁRIO
    public DadosResponseUsuario deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com esse id."));
        usuario.excluir();
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return toResponseUsuario(usuario);
    }

}

