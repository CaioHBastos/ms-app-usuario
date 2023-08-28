package br.com.bts.msusuario.usecase.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class Usuario {

    private String id;
    private String cpf;
    private String email;
    private String nomeCompleto;
    private String senha;
    private String apelido;
    private String dataNascimento;
    private String celular;
    private Genero genero;
    private String dataCadastro;
    private String dataAtualizacao;
    private Endereco endereco;
}
