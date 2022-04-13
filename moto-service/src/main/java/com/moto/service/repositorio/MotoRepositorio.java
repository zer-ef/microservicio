package com.moto.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moto.service.entity.Moto;

@Repository
public interface MotoRepositorio extends JpaRepository<Moto, Integer> {
// un suario tiene muchas motos 
	List<Moto> findByUsuarioId(int usuarioId);

}
