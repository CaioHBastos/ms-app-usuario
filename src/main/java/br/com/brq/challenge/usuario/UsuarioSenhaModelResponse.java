package br.com.brq.challenge.usuario;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UsuarioSenhaModelResponse(String codigoSeguranca) {
}
