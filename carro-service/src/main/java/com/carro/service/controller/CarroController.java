package com.carro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entity.Carro;
import com.carro.service.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private CarroService carroService;

	@GetMapping
	public ResponseEntity<List<Carro>> listarCarro() {
		List<Carro> carros = carroService.getAll();

		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.ok(carros);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Carro> obtenerCarro(@PathVariable("id") int id) {
		Carro carro = carroService.getCarroById(id);

		if (carro == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carro);
	}

	@PostMapping
	public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro) {
		Carro nuevoCarro = carroService.save(carro);
		return ResponseEntity.ok(nuevoCarro);
	}

	@GetMapping("usuario/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarroPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Carro> carros = carroService.byUsuarioId(id);
// si esta lista obtenida de carros es bacia
		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.ok(carros);
	}
}
