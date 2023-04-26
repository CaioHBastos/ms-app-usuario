package br.com.brq.challenge.usuario;

import lombok.Getter;

@Getter
public class EnderecoInput {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
}
