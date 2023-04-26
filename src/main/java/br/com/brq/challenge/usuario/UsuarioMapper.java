package br.com.brq.challenge.usuario;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioMapper {
    public static UsuarioEntity toEntity(UsuarioInput usuarioInput) {
        return UsuarioEntity.builder()
                .id(UUID.randomUUID().toString())
                .cpf(usuarioInput.getCpf())
                .email(usuarioInput.getEmail())
                .nomeCompleto(usuarioInput.getNomeCompleto())
                .senha(usuarioInput.getSenha())
                .apelido(usuarioInput.getApelido())
                .dataNascimento(usuarioInput.getDataNascimento())
                .celular(usuarioInput.getCelular())
                .sexo(SexoEnum.buscarCodigo(usuarioInput.getSexo()))
                .dataCadastro(OffsetDateTime.now().with(ChronoField.MILLI_OF_SECOND, 0))
                .endereco(EnderecoMapper.toEntity(usuarioInput.getEndereco()))
                .build();
    }
}
