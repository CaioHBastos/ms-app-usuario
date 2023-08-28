package br.com.bts.msusuario.usecase.gateway;

import br.com.bts.msusuario.usecase.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Usuario salvarUsuario(Usuario usuarioRequest);

    Boolean cpfIsPresent(Usuario usuarioRequest);

    Boolean emailIsPresent(Usuario usuarioRequest);

    List<Usuario> buscarUsuarios();

    Optional<Usuario> buscarUsuario(String idUsuario);

    void deletarUsuarioPorId(String idUsuario);

    Usuario atualizarUsuario(Usuario usuarioRequest);
}
