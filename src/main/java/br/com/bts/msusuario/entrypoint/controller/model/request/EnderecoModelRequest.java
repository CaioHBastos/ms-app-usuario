package br.com.bts.msusuario.entrypoint.controller.model.request;

public record EnderecoModelRequest(String cep, String logradouro, String numero, String bairro, String complemento,
                                   String cidade, String estado, String pais) {
}
