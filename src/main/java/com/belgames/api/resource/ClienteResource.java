package com.belgames.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.belgames.api.event.RecursoCriadoEvent;
import com.belgames.api.model.Cliente;
import com.belgames.api.repository.ClienteRepository;
import com.belgames.api.service.ClienteService;

@RestController
@Validated
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> listar() {
		List<Cliente> cliente = clienteRepository.findAll();
		return cliente;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return !cliente.isEmpty() ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente pessoaSalva = clienteRepository.save(cliente);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente clienteNovo = clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(clienteNovo);
	}
	
	@PutMapping("/{id}/{status}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarStatus(@PathVariable Long id, @Valid @RequestBody Boolean status) {
		clienteService.atualizarStatus(id, status);
	}

}
