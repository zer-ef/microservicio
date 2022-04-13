package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entity.Moto;
import com.moto.service.service.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoContoller {
	@Autowired
	private MotoService motoService;

	@GetMapping
	public ResponseEntity<List<Moto>> listarCarro() {
		List<Moto> motos = motoService.getAll();

		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.ok(motos);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerCarro(@PathVariable("id") int id) {
		Moto moto = motoService.getCarroById(id);

		if (moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(moto);
	}

	@PostMapping
	public ResponseEntity<Moto> guardarCarro(@RequestBody Moto moto) {
		Moto nuevoMoto = motoService.save(moto);
		return ResponseEntity.ok(nuevoMoto);
	}

	@GetMapping("usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotoPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Moto> motos = motoService.byUsuarioId(id);
// si esta lista obtenida de carros es bacia
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.ok(motos);
	}
}