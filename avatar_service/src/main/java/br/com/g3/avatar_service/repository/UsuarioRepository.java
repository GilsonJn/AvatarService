package br.com.g3.avatar_service.repository;

import br.com.g3.avatar_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
