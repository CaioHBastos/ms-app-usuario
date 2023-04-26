package br.com.brq.challenge.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexoEnum {

    ERRO("X", 0),
    MASCULINO("M", 1),
    FEMININO("F", 2),
    NAO_BINARIO("B", 3),
    PREFIRO_NAO_INFORMAR("N", 4);

    private final String sigla;
    private final Integer codigo;

    public static Integer buscarCodigo(String siglaSexo) {
        for (SexoEnum sexoEnum : values()) {
            if (sexoEnum.getSigla().equalsIgnoreCase(siglaSexo)) {
                return sexoEnum.getCodigo();
            }
        }

        return ERRO.getCodigo();
    }
}
