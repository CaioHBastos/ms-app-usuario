package br.com.bts.msusuario.entrypoint.controller.model.request;

public record UsuarioModelRequest(String cpf, String email, String nomeCompleto, String senha, String apelido,
                                  String dataNascimento, String celular, String genero, EnderecoModelRequest endereco) {
}
