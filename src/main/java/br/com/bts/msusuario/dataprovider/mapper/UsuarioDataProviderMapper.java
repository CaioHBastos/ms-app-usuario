package br.com.bts.msusuario.dataprovider.mapper;

import br.com.bts.msusuario.dataprovider.entity.UsuarioEntity;
import br.com.bts.msusuario.usecase.domain.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioDataProviderMapper {

    public static UsuarioEntity toEntity(Usuario usuarioRequest) {
        return UsuarioEntity.builder()
                .id(usuarioRequest.getId())
                .cpf(usuarioRequest.getCpf())
                .email(usuarioRequest.getEmail())
                .nomeCompleto(usuarioRequest.getNomeCompleto())
                .senha(usuarioRequest.getSenha())
                .apelido(usuarioRequest.getApelido())
                .dataNascimento(usuarioRequest.getDataNascimento())
                .celular(usuarioRequest.getCelular())
                .genero(usuarioRequest.getGenero().getCodigo())
                .dataCadastro(usuarioRequest.getDataCadastro())
                .dataAtualizacao(usuarioRequest.getDataAtualizacao())
                .endereco(EnderecoDataProviderMapper.toEntity(usuarioRequest.getEndereco()))
                .build();
    }

    public static List<Usuario> toDomain(List<UsuarioEntity> usuariosEntity) {
        return usuariosEntity.stream()
                .map(UsuarioDataProviderMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static Usuario toDomain(UsuarioEntity usuarioCadastrado) {
        return Usuario.builder()
                .id(usuarioCadastrado.getId())
                .cpf(usuarioCadastrado.getCpf())
                .email(usuarioCadastrado.getEmail())
                .nomeCompleto(usuarioCadastrado.getNomeCompleto())
                .senha(usuarioCadastrado.getSenha())
                .apelido(usuarioCadastrado.getApelido())
                .dataNascimento(usuarioCadastrado.getDataNascimento())
                .celular(usuarioCadastrado.getCelular())
                .genero(GeneroDataProviderMapper.toDomain(usuarioCadastrado))
                .dataCadastro(usuarioCadastrado.getDataCadastro())
                .endereco(EnderecoDataProviderMapper.toDomain(usuarioCadastrado.getEndereco()))
                .build();
    }
}
