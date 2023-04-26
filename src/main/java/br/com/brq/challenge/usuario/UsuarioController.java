package br.com.brq.challenge.usuario;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequestMapping(value = "/challengebrq/v1/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<UsuarioEntity> cadastrar(@RequestBody UsuarioInput usuarioInput) {

        usuarioRepository.findByCpf(usuarioInput.getCpf())
                .ifPresent(usuario -> {
                    throw new RuntimeException("CPF ja cadastrado.");
                });

        usuarioRepository.findByEmail(usuarioInput.getEmail())
                .ifPresent(usuario -> {
                    throw new RuntimeException("E-mail ja cadastrado.");
                });

        if (LocalDate.parse(usuarioInput.getDataNascimento()).isAfter(LocalDate.now())) {
            throw new RuntimeException("A data de nascimento informada nao pode ser futura.");
        }

        var usuario = UsuarioMapper.toEntity(usuarioInput);
        var usuarioCadastrado = usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuarioCadastrado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResumidoModelResponse>> listar() {

        var usuariosEntity = usuarioRepository.findAll();

        var usuarios = usuariosEntity.stream()
                .map(usuario -> new UsuarioResumidoModelResponse(
                        usuario.getId(), usuario.getCpf(), usuario.getEmail(), usuario.getNomeCompleto()
                )).collect(Collectors.toList());

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("{id_usuario}")
    public ResponseEntity<UsuarioEntity> detalhar(@PathVariable("id_usuario") String id) {

       var usuario = usuarioRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("{id_usuario}")
    public ResponseEntity<UsuarioEntity> remover(@PathVariable("id_usuario") String id) {

        usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));
        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id_usuario}")
    public ResponseEntity<UsuarioEntity> atualizar(@PathVariable("id_usuario") String id,
                                                 @RequestBody UsuarioUpdateInput usuarioUpdateInput) {

        var usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));

        if (StringUtils.isNotBlank(usuarioUpdateInput.getNomeCompleto())) {
            usuarioEntity.setNomeCompleto(usuarioUpdateInput.getNomeCompleto());
        }

        if (StringUtils.isNotBlank(usuarioUpdateInput.getApelido())) {
            usuarioEntity.setApelido(usuarioUpdateInput.getApelido());
        }

        if (StringUtils.isNotBlank(usuarioUpdateInput.getDataNascimento())) {
            usuarioEntity.setDataNascimento(usuarioUpdateInput.getDataNascimento());
        }

        if (Objects.nonNull(usuarioUpdateInput.getCelular())) {
            usuarioEntity.setCelular(usuarioUpdateInput.getCelular());
        }

        if (StringUtils.isNotBlank(usuarioUpdateInput.getSexo())) {
            usuarioEntity.setSexo(SexoEnum.buscarCodigo(usuarioUpdateInput.getSexo()));
        }

        if (Objects.nonNull(usuarioUpdateInput.getEndereco())) {
            EnderecoInput enderecoInput = usuarioUpdateInput.getEndereco();

            if (StringUtils.isNotBlank(enderecoInput.getLogradouro())) {
                usuarioEntity.getEndereco().setLogradouro(enderecoInput.getLogradouro());
            }

            if (StringUtils.isNotBlank(enderecoInput.getNumero())) {
                usuarioEntity.getEndereco().setNumero(enderecoInput.getNumero());
            }

            if (StringUtils.isNotBlank(enderecoInput.getBairro())) {
                usuarioEntity.getEndereco().setBairro(enderecoInput.getBairro());
            }

            if (StringUtils.isNotBlank(enderecoInput.getCidade())) {
                usuarioEntity.getEndereco().setCidade(enderecoInput.getCidade());
            }

            if (StringUtils.isNotBlank(enderecoInput.getEstado())) {
                usuarioEntity.getEndereco().setEstado(enderecoInput.getEstado());
            }

            if (StringUtils.isNotBlank(enderecoInput.getPais())) {
                usuarioEntity.getEndereco().setPais(enderecoInput.getPais());
            }

            if (StringUtils.isNotBlank(enderecoInput.getComplemento())) {
                usuarioEntity.getEndereco().setComplemento(enderecoInput.getComplemento());
            }

            if (StringUtils.isNotBlank(enderecoInput.getCep())) {
                usuarioEntity.getEndereco().setCep(enderecoInput.getCep());
            }
        }

        usuarioEntity.setDataAtualizacao(OffsetDateTime.now().with(ChronoField.MILLI_OF_SECOND, 0));
        usuarioRepository.save(usuarioEntity);

        return ResponseEntity.ok(usuarioEntity);
    }

    @PutMapping("{id_usuario}/senhas")
    public ResponseEntity<UsuarioEntity> alterarSenha(@PathVariable("id_usuario") String id,
                                                      @RequestBody UsuarioSenhaUpdateInput usuarioSenhaUpdateInput) {

        var usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));

        if (isSenhaDiferente(usuarioEntity.getSenha(), usuarioSenhaUpdateInput.getSenhaAtual())) {
            throw new RuntimeException("A senha informada é diferente da senha cadastrada.");
        }

        usuarioEntity.setDataAtualizacao(OffsetDateTime.now().with(ChronoField.MILLI_OF_SECOND, 0));
        usuarioEntity.setSenha(usuarioSenhaUpdateInput.getNovaSenha());

        usuarioRepository.save(usuarioEntity);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id_usuario}/senhas")
    public ResponseEntity<UsuarioSenhaModelResponse> getCodigoSegurancaSenha(@PathVariable("id_usuario") String id) {

        var usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));

        var usuario = new UsuarioSenhaModelResponse(UUID.randomUUID().toString());

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("{id_usuario}/senhas")
    public ResponseEntity<UsuarioEntity> criarNovaSenha(@PathVariable("id_usuario") String id,
                                                        @RequestBody UsuarioSenhaInput usuarioSenhaInput) {

        var usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));

        Pattern UUID_REGEX = Pattern.compile(
                "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
        );

        if (!UUID_REGEX.matcher(usuarioSenhaInput.getCodigoSeguranca()).matches()) {
            throw new RuntimeException("O codigo de seguranca nao é valido.");
        }

        usuarioEntity.setDataAtualizacao(OffsetDateTime.now().with(ChronoField.MILLI_OF_SECOND, 0));
        usuarioEntity.setSenha(usuarioSenhaInput.getNovaSenha());

        usuarioRepository.save(usuarioEntity);

        return new ResponseEntity<>(usuarioEntity, HttpStatus.CREATED);
    }

    private static boolean isSenhaDiferente(String senhaUsuarioBd, String senhaAtual) {
        return !senhaUsuarioBd.equals(senhaAtual);
    }
}
