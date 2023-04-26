package br.com.brq.challenge.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UsuarioSenhaUpdateInput {

    @JsonProperty("senha_atual")
    private String senhaAtual;

    @JsonProperty("nova_senha")
    private String novaSenha;
}
