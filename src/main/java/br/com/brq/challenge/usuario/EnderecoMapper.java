package br.com.brq.challenge.usuario;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnderecoMapper {

    public static EnderecoEntity toEntity(EnderecoInput enderecoInput) {
        return EnderecoEntity.builder()
                .cep(enderecoInput.getCep())
                .logradouro(enderecoInput.getLogradouro())
                .numero(enderecoInput.getNumero())
                .bairro(enderecoInput.getBairro())
                .complemento(enderecoInput.getComplemento())
                .cidade(enderecoInput.getCidade())
                .pais(enderecoInput.getPais())
                .estado(enderecoInput.getEstado())
                .cidade(enderecoInput.getCidade())
                .build();
    }
}
