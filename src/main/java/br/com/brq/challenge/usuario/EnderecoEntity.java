package br.com.brq.challenge.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnderecoEntity {

    @Column(name = "endereco_cep", length = 8)
    private String cep;

    @Column(name = "endereco_logradouro", length = 100)
    private String logradouro;

    @Column(name = "endereco_numero", length = 10)
    private String numero;

    @Column(name = "endereco_complemento", length = 20)
    private String complemento;

    @Column(name = "endereco_bairro", length = 20)
    private String bairro;

    @Column(name = "endereco_cidade", length = 20)
    private String cidade;

    @Column(name = "endereco_estado", length = 2)
    private String estado;

    @Column(name = "endereco_pais", length = 2)
    private String pais;
}
