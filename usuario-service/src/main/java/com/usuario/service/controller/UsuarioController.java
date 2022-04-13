package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entity.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuario() {
		List<Usuario> usuarios = usuarioService.getAll();

		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.ok(usuarios);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
		Usuario usuario = usuarioService.getUsuarioById(id);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}

	@GetMapping("/carros/{usuarioid}")
	public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioid") int id) {
		Usuario usuario = usuarioService.getUsuarioById(id);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros = usuarioService.getCarro(id);
		return ResponseEntity.ok(carros);
	}

	@GetMapping("/motos/{usuarioid}")
	public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioid") int id) {
		Usuario usuario = usuarioService.getUsuarioById(id);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos = usuarioService.getMoto(id);
		return ResponseEntity.ok(motos);
	}
//trabajando con fignClient
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> guardarCarroo(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro) {
		Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}

	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) {
		Moto nuevoMoto = usuarioService.saveMoto(usuarioId, moto);
		return ResponseEntity.ok(nuevoMoto);
	}
	
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId){
		Map<String, Object> resultado=usuarioService.getUsuarioAndVehiculo(usuarioId);
		return ResponseEntity.ok(resultado);
	}

}
