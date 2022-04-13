package com.carro.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carro.service.entity.Carro;
import com.carro.service.repositorio.CarroRepositorio;

@Service
public class CarroService {

	@Autowired
	private CarroRepositorio carroRepositorio;

	public List<Carro> getAll() {
		return carroRepositorio.findAll();
	}

	public Carro getCarroById(int id) {
		return carroRepositorio.findById(id).orElse(null);
	}

	public Carro save(Carro carro) {
		Carro nuevoUsuario = carroRepositorio.save(carro);
		return nuevoUsuario;
	}

	// para mostrar los carros de un usuario
	public List<Carro> byUsuarioId(int usuarioId) {
		return carroRepositorio.findByUsuarioId(usuarioId);

	}
}
