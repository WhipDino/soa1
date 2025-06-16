package com.faculdade.vicioapostas.controller;

import com.faculdade.vicioapostas.model.SessaoApoio;
import com.faculdade.vicioapostas.model.Usuario;
import com.faculdade.vicioapostas.service.SessaoApoioService;
import com.faculdade.vicioapostas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
@CrossOrigin(origins = "*")
public class SessaoApoioController {
    
    @Autowired
    private SessaoApoioService sessaoApoioService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<SessaoApoio>> listarTodas() {
        List<SessaoApoio> sessoes = sessaoApoioService.listarTodas();
        return ResponseEntity.ok(sessoes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SessaoApoio> buscarPorId(@PathVariable Long id) {
        return sessaoApoioService.buscarPorId(id)
                .map(sessao -> ResponseEntity.ok(sessao))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<SessaoApoio>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return usuarioService.buscarPorId(usuarioId)
                .map(usuario -> {
                    List<SessaoApoio> sessoes = sessaoApoioService.buscarPorUsuario(usuario);
                    return ResponseEntity.ok(sessoes);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<SessaoApoio> criar(@RequestBody SessaoApoio sessaoApoio) {
        SessaoApoio novaSessao = sessaoApoioService.salvar(sessaoApoio);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSessao);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SessaoApoio> atualizar(@PathVariable Long id, @RequestBody SessaoApoio sessaoApoio) {
        try {
            SessaoApoio sessaoAtualizada = sessaoApoioService.atualizar(id, sessaoApoio);
            return ResponseEntity.ok(sessaoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        sessaoApoioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

