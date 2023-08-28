package br.com.bts.msusuario.usecase;

import br.com.bts.msusuario.usecase.domain.Usuario;
import br.com.bts.msusuario.usecase.gateway.UsuarioGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public UsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuarioRequest) {

        if (usuarioGateway.cpfIsPresent(usuarioRequest)) {
            throw new RuntimeException("O CPF informado já tem cadastro na plataforma.");
        }

        if (usuarioGateway.emailIsPresent(usuarioRequest)) {
            throw new RuntimeException("O E-mail informado já tem cadastro na plataforma.");
        }

        if (LocalDate.parse(usuarioRequest.getDataNascimento()).isAfter(LocalDate.now()) ||
                LocalDate.parse(usuarioRequest.getDataNascimento()).isEqual(LocalDate.now())) {
            throw new RuntimeException("A data de nascimento informada nao pode ser igual ou futura a data atual.");
        }

        Optional<GeneroEnum> generoEnum = GeneroEnum.buscarDadosGenero(usuarioRequest);

        if (generoEnum.isEmpty()) {
            throw new RuntimeException("A sigla do genêro informada não é aceita na plataforma.");
        }

        usuarioRequest.setId(UUID.randomUUID().toString());
        usuarioRequest.getGenero().setCodigo(generoEnum.get().getCodigo());
        usuarioRequest.setDataCadastro(gerarDataHoraFormatada());

        Usuario usuarioResult = usuarioGateway.salvarUsuarioDB(usuarioRequest);
        usuarioResult.getGenero().setSigla(generoEnum.get().getSigla());

        return usuarioResult;
    }

    private static String gerarDataHoraFormatada() {
        DateTimeFormatter dataHoraFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss Z");
        return dataHoraFormato.format(ZonedDateTime.now());
    }
}
