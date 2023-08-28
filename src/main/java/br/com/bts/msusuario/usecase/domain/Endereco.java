package br.com.bts.msusuario.usecase.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Endereco {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    private String complemento;
}
