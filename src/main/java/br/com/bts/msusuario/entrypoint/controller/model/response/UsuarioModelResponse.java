package br.com.bts.msusuario.entrypoint.controller.model.response;

public record UsuarioModelResponse(String id, String nomeCompleto, String cpf, String email, String apelido,
                                   String dataNascimento, String celular, String genero, String dataCastro,
                                   EnderecoModelResponse endereco) {
}
