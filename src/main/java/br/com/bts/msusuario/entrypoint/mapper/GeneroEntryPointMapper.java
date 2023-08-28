package br.com.bts.msusuario.entrypoint.mapper;

import br.com.bts.msusuario.usecase.domain.Genero;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneroEntryPointMapper {

    public static Genero toDomain(String siglaGenero) {
        var genero = new Genero();
        genero.setSigla(siglaGenero);

        return genero;
    }
}
