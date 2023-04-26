package br.com.brq.challenge.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UsuarioUpdateInput {

    @JsonProperty("nome_completo")
    private String nomeCompleto;
    private String apelido;

    @JsonProperty("data_nascimento")
    private String dataNascimento;

    private Long celular;
    private String sexo;

    private EnderecoInput endereco;
}
