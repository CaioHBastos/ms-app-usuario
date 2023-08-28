package br.com.bts.msusuario.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioModelRequest(
        String cpf,
        String email,
        @JsonProperty("nome_completo") String nomeCompleto,
        String senha,
        String apelido,
        @JsonProperty("data_nascimento") String dataNascimento,
        String celular,
        String genero,
        EnderecoModelRequest endereco) {
}
