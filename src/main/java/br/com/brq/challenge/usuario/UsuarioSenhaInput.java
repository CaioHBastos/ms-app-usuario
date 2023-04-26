package br.com.brq.challenge.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UsuarioSenhaInput {

    @JsonProperty("codigo_seguranca")
    private String codigoSeguranca;

    @JsonProperty("nova_senha")
    private String novaSenha;
}
