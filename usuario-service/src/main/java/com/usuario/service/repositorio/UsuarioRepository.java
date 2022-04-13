package com.usuario.service.repositorio;

import org.springframework.stereotype.Repository;

import com.usuario.service.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario,Integer>{

}
