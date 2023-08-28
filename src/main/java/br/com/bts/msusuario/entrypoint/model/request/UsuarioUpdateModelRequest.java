package br.com.bts.msusuario.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioUpdateModelRequest(
        @JsonProperty("nome_completo") String nomeCompleto,
        String apelido,
        @JsonProperty("data_nascimento") String dataNascimento,
        String celular,
        String genero,
        EnderecoModelRequest endereco
) {
}
