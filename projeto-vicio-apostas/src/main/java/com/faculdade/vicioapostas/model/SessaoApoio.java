package com.faculdade.vicioapostas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessoes_apoio")
public class SessaoApoio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(name = "data_sessao")
    private LocalDateTime dataSessao;
    
    @Enumerated(EnumType.STRING)
    private TipoSessao tipo;
    
    @Column(name = "observacoes")
    private String observacoes;
    
    public SessaoApoio() {
        this.dataSessao = LocalDateTime.now();
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDateTime getDataSessao() {
        return dataSessao;
    }
    
    public void setDataSessao(LocalDateTime dataSessao) {
        this.dataSessao = dataSessao;
    }
    
    public TipoSessao getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoSessao tipo) {
        this.tipo = tipo;
    }
    
    public String getObservacoes() {
        return observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}

