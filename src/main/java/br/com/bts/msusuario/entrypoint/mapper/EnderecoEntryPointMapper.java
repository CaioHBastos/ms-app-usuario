package br.com.bts.msusuario.entrypoint.mapper;

import br.com.bts.msusuario.entrypoint.model.request.EnderecoModelRequest;
import br.com.bts.msusuario.entrypoint.model.response.EnderecoModelResponse;
import br.com.bts.msusuario.usecase.domain.Endereco;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnderecoEntryPointMapper {

    public static Endereco toDomain(EnderecoModelRequest endereco) {
        if (Objects.isNull(endereco)) {
            return null;
        }

        return Endereco.builder()
                .cep(endereco.cep())
                .logradouro(endereco.logradouro())
                .numero(endereco.numero())
                .bairro(endereco.bairro())
                .complemento(endereco.complemento())
                .cidade(endereco.cidade())
                .estado(endereco.estado())
                .pais(endereco.pais())
                .build();
    }

    public static EnderecoModelResponse toModel(Endereco endereco) {
        return new EnderecoModelResponse(
                endereco.getCep(), endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(),
                endereco.getComplemento(), endereco.getCidade(), endereco.getEstado(), endereco.getPais()
        );
    }
}
