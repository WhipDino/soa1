package com.faculdade.vicioapostas.service;

import com.faculdade.vicioapostas.model.SessaoApoio;
import com.faculdade.vicioapostas.model.Usuario;
import com.faculdade.vicioapostas.repository.SessaoApoioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoApoioService {
    
    @Autowired
    private SessaoApoioRepository sessaoApoioRepository;
    
    public List<SessaoApoio> listarTodas() {
        return sessaoApoioRepository.findAll();
    }
    
    public Optional<SessaoApoio> buscarPorId(Long id) {
        return sessaoApoioRepository.findById(id);
    }
    
    public List<SessaoApoio> buscarPorUsuario(Usuario usuario) {
        return sessaoApoioRepository.findByUsuarioOrderByDataSessaoDesc(usuario);
    }
    
    public SessaoApoio salvar(SessaoApoio sessaoApoio) {
        return sessaoApoioRepository.save(sessaoApoio);
    }
    
    public SessaoApoio atualizar(Long id, SessaoApoio sessaoAtualizada) {
        return sessaoApoioRepository.findById(id)
                .map(sessao -> {
                    sessao.setDescricao(sessaoAtualizada.getDescricao());
                    sessao.setTipo(sessaoAtualizada.getTipo());
                    sessao.setObservacoes(sessaoAtualizada.getObservacoes());
                    return sessaoApoioRepository.save(sessao);
                })
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
    }
    
    public void deletar(Long id) {
        sessaoApoioRepository.deleteById(id);
    }
}

