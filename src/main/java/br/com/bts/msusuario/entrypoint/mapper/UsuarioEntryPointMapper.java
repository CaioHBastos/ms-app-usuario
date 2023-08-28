package br.com.bts.msusuario.entrypoint.mapper;

import br.com.bts.msusuario.entrypoint.model.request.UsuarioModelRequest;
import br.com.bts.msusuario.entrypoint.model.request.UsuarioUpdateModelRequest;
import br.com.bts.msusuario.entrypoint.model.response.UsuarioModelResponse;
import br.com.bts.msusuario.entrypoint.model.response.UsuarioResumidoModelResponse;
import br.com.bts.msusuario.usecase.domain.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    public static Usuario toDomain(UsuarioUpdateModelRequest usuarioModelRequest) {
        return Usuario.builder()
                .nomeCompleto(usuarioModelRequest.nomeCompleto())
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
                usuarioCadastrado.getDataCadastro(), usuarioCadastrado.getDataAtualizacao(),
                EnderecoEntryPointMapper.toModel(usuarioCadastrado.getEndereco())
        );
    }

    public static List<UsuarioResumidoModelResponse> toModel(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioEntryPointMapper::toModelResumido)
                .collect(Collectors.toList());
    }

    public static UsuarioResumidoModelResponse toModelResumido(Usuario usuario) {
        return new UsuarioResumidoModelResponse(
                usuario.getId(), usuario.getCpf(), usuario.getEmail(),
                usuario.getNomeCompleto()
        );
    }
}
