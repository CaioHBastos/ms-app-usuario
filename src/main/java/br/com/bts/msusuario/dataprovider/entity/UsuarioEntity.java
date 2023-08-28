package br.com.bts.msusuario.dataprovider.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "nome_completo", length = 100, nullable = false)
    private String nomeCompleto;

    @Column(name = "senha", length = 100, nullable = false)
    private String senha;

    @Column(name = "apelido", length = 20)
    private String apelido;

    @Column(name = "data_nascimento", length = 12, nullable = false)
    private String dataNascimento;

    @Column(name = "celular", length = 13, nullable = false)
    private String celular;

    @Column(name = "genero", length = 1, nullable = false)
    private Integer genero;

    @Column(name = "data_cadastro", length = 30, nullable = false)
    private String dataCadastro;

    @Column(name = "data_atualizacao", length = 30)
    private String dataAtualizacao;

    @Embedded
    private EnderecoEntity endereco;
}
