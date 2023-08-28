package br.com.bts.msusuario.entrypoint.controller;

import br.com.bts.msusuario.entrypoint.controller.model.request.UsuarioModelRequest;
import br.com.bts.msusuario.entrypoint.controller.model.response.UsuarioModelResponse;
import br.com.bts.msusuario.entrypoint.mapper.UsuarioEntryPointMapper;
import br.com.bts.msusuario.usecase.UsuarioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "challengebrq/v1/usuarios")
@RestController
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioModelResponse> cadastrar(@RequestBody UsuarioModelRequest usuarioModelRequest) {
        var usuario = UsuarioEntryPointMapper.toDomain(usuarioModelRequest);
        var usuarioCadastrado = usuarioUseCase.cadastrarUsuario(usuario);
        var usuarioResponse = UsuarioEntryPointMapper.toModel(usuarioCadastrado);

        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }
}
