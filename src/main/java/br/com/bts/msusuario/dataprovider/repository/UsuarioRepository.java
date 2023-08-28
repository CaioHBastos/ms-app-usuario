package br.com.bts.msusuario.dataprovider.repository;

import br.com.bts.msusuario.dataprovider.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    Optional<UsuarioEntity> findByCpf(String cpf);

    Optional<UsuarioEntity> findByEmail(String email);
}
