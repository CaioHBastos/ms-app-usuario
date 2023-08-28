package br.com.bts.msusuario.dataprovider.service;

import br.com.bts.msusuario.dataprovider.entity.UsuarioEntity;
import br.com.bts.msusuario.dataprovider.mapper.UsuarioDataProviderMapper;
import br.com.bts.msusuario.dataprovider.repository.UsuarioRepository;
import br.com.bts.msusuario.usecase.domain.Usuario;
import br.com.bts.msusuario.usecase.gateway.UsuarioGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioDataProvider implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDataProvider(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Usuario salvarUsuarioDB(Usuario usuarioRequest) {
        var usuarioEntity = UsuarioDataProviderMapper.toEntity(usuarioRequest);
        var usuarioCadastrado = usuarioRepository.save(usuarioEntity);

        return UsuarioDataProviderMapper.toDomain(usuarioCadastrado);
    }

    @Override
    public Boolean cpfIsPresent(Usuario usuarioRequest) {
        return usuarioRepository.findByCpf(usuarioRequest.getCpf()).isPresent();
    }

    @Override
    public Boolean emailIsPresent(Usuario usuarioRequest) {
        return usuarioRepository.findByEmail(usuarioRequest.getEmail()).isPresent();
    }
}
