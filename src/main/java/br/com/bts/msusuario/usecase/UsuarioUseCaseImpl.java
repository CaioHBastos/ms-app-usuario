package br.com.bts.msusuario.usecase;

import br.com.bts.msusuario.usecase.domain.Usuario;
import br.com.bts.msusuario.usecase.gateway.UsuarioGateway;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
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

        GeneroEnum generoEnum = GeneroEnum.buscarDadosGenero(usuarioRequest)
                .orElseThrow(() -> new RuntimeException("A sigla do genêro informada não é aceita na plataforma."));

        usuarioRequest.setId(UUID.randomUUID().toString());
        usuarioRequest.getGenero().setCodigo(generoEnum.getCodigo());
        usuarioRequest.setDataCadastro(gerarDataHoraFormatada());

        Usuario usuarioResult = usuarioGateway.salvarUsuario(usuarioRequest);
        usuarioResult.getGenero().setSigla(generoEnum.getSigla());

        return usuarioResult;
    }

    @Override
    public List<Usuario> buscarUsuarios() {
        return usuarioGateway.buscarUsuarios();
    }

    @Override
    public Usuario buscarUsuario(String idUsuario) {
        return usuarioGateway.buscarUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("O usuario informado nao tem cadastro na plataforma."));
    }

    @Override
    public void deletarUsuario(String idUsuario) {
        usuarioGateway.buscarUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("O usuario informado nao tem cadastro na plataforma."));

        usuarioGateway.deletarUsuarioPorId(idUsuario);
    }

    @Override
    public Usuario atualizarUsuario(String idUsuario, Usuario usuarioRequest) {
        var usuarioDb = usuarioGateway.buscarUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("O usuario informado nao tem cadastro na plataforma."));

        if (StringUtils.isNotBlank(usuarioRequest.getNomeCompleto())) {
            usuarioDb.setNomeCompleto(usuarioRequest.getNomeCompleto());
        }

        if (StringUtils.isNotBlank(usuarioRequest.getApelido())) {
            usuarioDb.setApelido(usuarioRequest.getApelido());
        }

        if (StringUtils.isNotBlank(usuarioRequest.getDataNascimento())) {
            usuarioDb.setDataNascimento(usuarioRequest.getDataNascimento());
        }

        if (Objects.nonNull(usuarioRequest.getCelular())) {
            usuarioDb.setCelular(usuarioRequest.getCelular());
        }

        if (StringUtils.isNotBlank(usuarioRequest.getGenero().getSigla())) {
            var generoEnum = GeneroEnum.buscarDadosGenero(usuarioRequest)
                    .orElseThrow(() -> new RuntimeException("A sigla do genêro informada não é aceita na plataforma."));

            usuarioDb.getGenero().setCodigo(generoEnum.getCodigo());
        }

        if (Objects.nonNull(usuarioRequest.getEndereco())) {
            usuarioDb.setEndereco(usuarioRequest.getEndereco());
        }

        usuarioDb.setDataAtualizacao(gerarDataHoraFormatada());

        var usuarioResult = usuarioGateway.atualizarUsuario(usuarioDb);

        if (StringUtils.isNotBlank(usuarioRequest.getGenero().getSigla())) {
            var generoEnum = GeneroEnum.buscarDadosGenero(usuarioRequest)
                    .orElseThrow(() -> new RuntimeException("A sigla do genêro informada não é aceita na plataforma."));

            usuarioResult.getGenero().setSigla(generoEnum.getSigla());
        }

        return usuarioResult;
    }

    private static String gerarDataHoraFormatada() {
        DateTimeFormatter dataHoraFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss Z");
        return dataHoraFormato.format(ZonedDateTime.now());
    }
}
