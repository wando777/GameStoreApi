package com.belgames.api.resource;

import java.util.List;
import java.util.Optional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belgames.api.event.RecursoCriadoEvent;
import com.belgames.api.model.Lancamento;
import com.belgames.api.repository.LancamentoRepository;

@RestController
@Validated
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Lancamento> listar() {
		List<Lancamento> lancamento = lancamentoRepository.findAll();
		return lancamento;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long id) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
		return !lancamento.isEmpty() ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}

}
