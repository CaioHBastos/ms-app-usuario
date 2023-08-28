package br.com.bts.msusuario.dataprovider.mapper;

import br.com.bts.msusuario.dataprovider.entity.UsuarioEntity;
import br.com.bts.msusuario.usecase.domain.Genero;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneroDataProviderMapper {

    public static Genero toDomain(UsuarioEntity usuarioCadastrado) {
        var genero = new Genero();
        genero.setCodigo(usuarioCadastrado.getGenero());

        return genero;
    }
}
