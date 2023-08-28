package br.com.bts.msusuario.entrypoint.controller;

import br.com.bts.msusuario.entrypoint.mapper.UsuarioEntryPointMapper;
import br.com.bts.msusuario.entrypoint.model.request.UsuarioModelRequest;
import br.com.bts.msusuario.entrypoint.model.request.UsuarioUpdateModelRequest;
import br.com.bts.msusuario.entrypoint.model.response.UsuarioModelResponse;
import br.com.bts.msusuario.entrypoint.model.response.UsuarioResumidoModelResponse;
import br.com.bts.msusuario.usecase.UsuarioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UsuarioResumidoModelResponse>> listar() {
        var usuarios = usuarioUseCase.buscarUsuarios();
        var usuariosResponse = UsuarioEntryPointMapper.toModel(usuarios);

        return ResponseEntity.ok(usuariosResponse);
    }

    @GetMapping("{idUsuario}")
    public ResponseEntity<UsuarioModelResponse> detalhar(@PathVariable String idUsuario) {
        var usuario = usuarioUseCase.buscarUsuario(idUsuario);
        var usuarioResponse = UsuarioEntryPointMapper.toModel(usuario);

        return ResponseEntity.ok(usuarioResponse);
    }

    @DeleteMapping("{idUsuario}")
    public ResponseEntity<UsuarioModelResponse> deletar(@PathVariable String idUsuario) {
        usuarioUseCase.deletarUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{idUsuario}")
    public ResponseEntity<UsuarioModelResponse> atualizar(@PathVariable String idUsuario,
                                                          @RequestBody UsuarioUpdateModelRequest usuarioModelRequest) {

        var usuario = UsuarioEntryPointMapper.toDomain(usuarioModelRequest);
        var usuarioAtualizado = usuarioUseCase.atualizarUsuario(idUsuario, usuario);
        var usuarioResponse = UsuarioEntryPointMapper.toModel(usuarioAtualizado);

        return ResponseEntity.ok(usuarioResponse);
    }
}
