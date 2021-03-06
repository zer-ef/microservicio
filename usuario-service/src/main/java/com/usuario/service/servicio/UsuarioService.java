package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorio.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CarroFeignClient carroFiegnCient;

	@Autowired
	private MotoFeignClient motoFeignClient;

//Trabajando con RestTemplate
	public List<Carro> getCarro(int usuarioId) {
		List<Carro> carros = restTemplate.getForObject("http://localhost:8088/carro/usuario/" + usuarioId, List.class);
		return carros;
	}

	public List<Moto> getMoto(int usuarioId) {
		List<Moto> motos = restTemplate.getForObject("http://localhost:8089/moto/usuario/" + usuarioId, List.class);
		return motos;
	}

// Trabajado con Feign CLient
	public Carro saveCarro(int usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFiegnCient.save(carro);
		return nuevoCarro;
	}

	public Moto saveMoto(int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevoMoto = motoFeignClient.save(moto);
		return nuevoMoto;
	}

	// obtener ambos vehiculo
	public Map<String, Object> getUsuarioAndVehiculo(int usuarioId) {
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

		if (usuario == null) {
			resultado.put("mensaje", "El usuario no existe");
			return resultado;
		}
		resultado.put("mensaje", usuario);
		List<Carro> carros = carroFiegnCient.getCarro(usuarioId);
		if (carros.isEmpty()) {
			resultado.put("carro", "El usuario no tiene carro");
		} else {
			resultado.put("carro", carros);
		}

		List<Moto> motos = motoFeignClient.getMoto(usuarioId);
		if (motos.isEmpty()) {
			resultado.put("moto", "El usuario no tiene carro");
		} else {
			resultado.put("moto", motos);
		}

		return resultado;

	}

//Trabajando para cada Microservicio
	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}

}
