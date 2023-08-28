package br.com.bts.msusuario.entrypoint.controller.model.response;

public record EnderecoModelResponse(String cep, String logradouro, String numero, String bairro, String complemento,
                                    String cidade, String estado, String pais) {
}
