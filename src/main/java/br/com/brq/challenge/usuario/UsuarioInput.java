package br.com.brq.challenge.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UsuarioInput {

    private String cpf;
    private String email;

    @JsonProperty("nome_completo")
    private String nomeCompleto;

    private String senha;
    private String apelido;

    @JsonProperty("data_nascimento")
    private String dataNascimento;

    private Long celular;
    private String sexo;

    private EnderecoInput endereco;
}
