package br.com.bts.msusuario.dataprovider.mapper;

import br.com.bts.msusuario.dataprovider.entity.UsuarioEntity;
import br.com.bts.msusuario.usecase.domain.Genero;
import br.com.bts.msusuario.usecase.domain.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
                .endereco(EnderecoDataProviderMapper.toEntity(usuarioRequest.getEndereco()))
                .build();
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
