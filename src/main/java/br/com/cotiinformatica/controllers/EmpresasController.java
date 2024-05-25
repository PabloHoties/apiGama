package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.repositories.EmpresaRepository;

@RestController
@RequestMapping(value = "/api/empresas")
public class EmpresasController {

	
	@GetMapping // Consultar
	public ResponseEntity<List<Empresa>> getAll() throws Exception {

		try {

			EmpresaRepository clienteRepository = new EmpresaRepository();
			List<Empresa> clientes = clienteRepository.findAll();

			if (clientes.size() == 0) // Se a lista está vazia
				// HTTP 204 - NO CONTENT
				return ResponseEntity.status(204).body(null);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body(clientes);
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}
	
}
