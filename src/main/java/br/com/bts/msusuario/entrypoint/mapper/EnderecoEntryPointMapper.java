package br.com.bts.msusuario.entrypoint.mapper;

import br.com.bts.msusuario.entrypoint.controller.model.request.EnderecoModelRequest;
import br.com.bts.msusuario.entrypoint.controller.model.response.EnderecoModelResponse;
import br.com.bts.msusuario.usecase.domain.Endereco;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnderecoEntryPointMapper {

    public static Endereco toDomain(EnderecoModelRequest endereco) {
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
