package br.com.brq.challenge.usuario;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UsuarioResumidoModelResponse(String id, String cpf, String email, String nomeCompleto) {
}
