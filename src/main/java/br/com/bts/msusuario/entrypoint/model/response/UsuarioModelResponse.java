package br.com.bts.msusuario.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioModelResponse(
        String id,
        @JsonProperty("nome_completo") String nomeCompleto,
        String cpf,
        String email,
        String apelido,
        @JsonProperty("data_nascimento") String dataNascimento,
        String celular,
        String genero,
        @JsonProperty("data_cadastro") String dataCastro,
        @JsonProperty("data_atualizacao") String dataAtualizacao,
        EnderecoModelResponse endereco) {
}
