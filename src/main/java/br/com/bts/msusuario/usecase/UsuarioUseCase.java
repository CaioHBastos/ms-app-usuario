package br.com.bts.msusuario.usecase;

import br.com.bts.msusuario.usecase.domain.Usuario;

import java.util.List;

public interface UsuarioUseCase {

    Usuario cadastrarUsuario(Usuario usuarioRequest);

    List<Usuario> buscarUsuarios();

    Usuario buscarUsuario(String idUsuario);

    void deletarUsuario(String idUsuario);

    Usuario atualizarUsuario(String idUsuario, Usuario usuarioRequest);
}
