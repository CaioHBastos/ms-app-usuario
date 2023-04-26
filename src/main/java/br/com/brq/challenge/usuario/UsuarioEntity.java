package br.com.brq.challenge.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

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
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "nome_completo", length = 100)
    private String nomeCompleto;

    @Column(name = "senha", length = 100)
    private String senha;

    @Column(name = "apelido", length = 20)
    private String apelido;

    @Column(name = "data_nascimento", length = 12)
    private String dataNascimento;

    @Column(name = "celular", length = 13)
    private Long celular;

    @Column(name = "sexo", length = 1)
    private Integer sexo;

    private OffsetDateTime dataCadastro;

    private OffsetDateTime dataAtualizacao;

    @Embedded
    private EnderecoEntity endereco;
}
