package br.com.bts.msusuario.usecase;

import br.com.bts.msusuario.usecase.domain.Usuario;
import lombok.Getter;

import java.util.Optional;

@Getter
public enum GeneroEnum {

    MASCULINO("M", 1),
    FEMININO("F", 2),
    NAO_BINARIO("B", 3),
    PREFIRO_NAO_INFORMAR("N", 4);

    private final String sigla;
    private final Integer codigo;

    GeneroEnum(String sigla, Integer codigo) {
        this.sigla = sigla;
        this.codigo = codigo;
    }


    public static Optional<GeneroEnum> buscarDadosGenero(Usuario usuarioRequest) {
        for (GeneroEnum generoEnum : values()) {
            if (usuarioRequest.getGenero().getSigla().equalsIgnoreCase(generoEnum.getSigla())) {
                return Optional.of(generoEnum);
            }
        }

        return Optional.empty();
    }
}
