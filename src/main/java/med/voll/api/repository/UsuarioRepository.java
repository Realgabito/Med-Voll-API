package med.voll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import med.voll.api.DTO.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    UserDetails findByUsername(String username);

}
