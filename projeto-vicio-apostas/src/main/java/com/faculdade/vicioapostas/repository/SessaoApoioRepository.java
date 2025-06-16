package com.faculdade.vicioapostas.repository;

import com.faculdade.vicioapostas.model.SessaoApoio;
import com.faculdade.vicioapostas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoApoioRepository extends JpaRepository<SessaoApoio, Long> {
    
    List<SessaoApoio> findByUsuario(Usuario usuario);
    
    List<SessaoApoio> findByUsuarioOrderByDataSessaoDesc(Usuario usuario);
}

