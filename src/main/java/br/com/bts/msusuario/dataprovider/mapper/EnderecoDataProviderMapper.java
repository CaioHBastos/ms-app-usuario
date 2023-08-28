package br.com.bts.msusuario.dataprovider.mapper;

import br.com.bts.msusuario.dataprovider.entity.EnderecoEntity;
import br.com.bts.msusuario.usecase.domain.Endereco;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnderecoDataProviderMapper {

    public static EnderecoEntity toEntity(Endereco enderecoRequest) {
        return EnderecoEntity.builder()
                .cep(enderecoRequest.getCep())
                .logradouro(enderecoRequest.getLogradouro())
                .numero(enderecoRequest.getNumero())
                .bairro(enderecoRequest.getBairro())
                .complemento(enderecoRequest.getComplemento())
                .cidade(enderecoRequest.getCidade())
                .estado(enderecoRequest.getEstado())
                .pais(enderecoRequest.getPais())
                .build();
    }

    public static Endereco toDomain(EnderecoEntity enderecoCadastrado) {
        return Endereco.builder()
                .cep(enderecoCadastrado.getCep())
                .logradouro(enderecoCadastrado.getLogradouro())
                .numero(enderecoCadastrado.getNumero())
                .bairro(enderecoCadastrado.getBairro())
                .complemento(enderecoCadastrado.getComplemento())
                .cidade(enderecoCadastrado.getCidade())
                .estado(enderecoCadastrado.getEstado())
                .pais(enderecoCadastrado.getPais())
                .build();
    }
}
