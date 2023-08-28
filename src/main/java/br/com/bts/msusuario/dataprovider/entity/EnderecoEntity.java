package br.com.bts.msusuario.dataprovider.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnderecoEntity {

    @Column(name = "endereco_cep", length = 8, nullable = false)
    private String cep;

    @Column(name = "endereco_logradouro", length = 100, nullable = false)
    private String logradouro;

    @Column(name = "endereco_numero", length = 10, nullable = false)
    private String numero;

    @Column(name = "endereco_complemento", length = 20)
    private String complemento;

    @Column(name = "endereco_bairro", length = 20, nullable = false)
    private String bairro;

    @Column(name = "endereco_cidade", length = 20, nullable = false)
    private String cidade;

    @Column(name = "endereco_estado", length = 2, nullable = false)
    private String estado;

    @Column(name = "endereco_pais", length = 2, nullable = false)
    private String pais;
}
