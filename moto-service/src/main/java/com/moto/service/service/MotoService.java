package com.moto.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entity.Moto;
import com.moto.service.repositorio.MotoRepositorio;

@Service
public class MotoService {

	@Autowired
	private MotoRepositorio motoRepositorio;

	public List<Moto> getAll() {
		return motoRepositorio.findAll();
	}

	public Moto getCarroById(int id) {
		return motoRepositorio.findById(id).orElse(null);
	}

	public Moto save(Moto moto) {
		Moto nuevoMoto = motoRepositorio.save(moto);
		return nuevoMoto;
	}

	// para mostrar los carros de un usuario
	public List<Moto> byUsuarioId(int usuarioId) {
		return motoRepositorio.findByUsuarioId(usuarioId);

	}
}