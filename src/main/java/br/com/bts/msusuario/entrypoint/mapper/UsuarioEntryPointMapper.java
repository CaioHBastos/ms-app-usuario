package br.com.bts.msusuario.entrypoint.mapper;

import br.com.bts.msusuario.entrypoint.controller.model.request.UsuarioModelRequest;
import br.com.bts.msusuario.entrypoint.controller.model.response.UsuarioModelResponse;
import br.com.bts.msusuario.usecase.domain.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioEntryPointMapper {

    public static Usuario toDomain(UsuarioModelRequest usuarioModelRequest) {
        return Usuario.builder()
                .cpf(usuarioModelRequest.cpf())
                .email(usuarioModelRequest.email())
                .nomeCompleto(usuarioModelRequest.nomeCompleto())
                .senha(usuarioModelRequest.senha())
                .apelido(usuarioModelRequest.apelido())
                .dataNascimento(usuarioModelRequest.dataNascimento())
                .celular(usuarioModelRequest.celular())
                .genero(GeneroEntryPointMapper.toDomain(usuarioModelRequest.genero()))
                .endereco(EnderecoEntryPointMapper.toDomain(usuarioModelRequest.endereco()))
                .build();
    }

    public static UsuarioModelResponse toModel(Usuario usuarioCadastrado) {
        return new UsuarioModelResponse(
                usuarioCadastrado.getId(), usuarioCadastrado.getNomeCompleto(), usuarioCadastrado.getCpf(),
                usuarioCadastrado.getEmail(), usuarioCadastrado.getApelido(), usuarioCadastrado.getDataNascimento(),
                usuarioCadastrado.getCelular(), usuarioCadastrado.getGenero().getSigla(),
                usuarioCadastrado.getDataCadastro(), EnderecoEntryPointMapper.toModel(usuarioCadastrado.getEndereco())
        );
    }
}
