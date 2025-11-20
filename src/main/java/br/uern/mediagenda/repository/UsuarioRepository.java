package br.uern.mediagenda.repository;

import br.uern.mediagenda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByLogin(String login);
    
    Optional<Usuario> findByEmail(String email);
    
    List<Usuario> findByAtivoTrue();
    
    boolean existsByLogin(String login);
    
    boolean existsByEmail(String email);
}