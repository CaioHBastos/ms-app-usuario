package br.com.bts.msusuario.usecase.gateway;

import br.com.bts.msusuario.usecase.domain.Usuario;

public interface UsuarioGateway {

    Usuario salvarUsuarioDB(Usuario usuarioRequest);

    Boolean cpfIsPresent(Usuario usuarioRequest);

    Boolean emailIsPresent(Usuario usuarioRequest);
}
