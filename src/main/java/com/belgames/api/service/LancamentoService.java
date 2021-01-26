package com.belgames.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belgames.api.model.Cliente;
import com.belgames.api.model.Lancamento;
import com.belgames.api.repository.ClienteRepository;
import com.belgames.api.repository.LancamentoRepository;
import com.belgames.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Optional<Cliente> cliente = clienteRepository.findById(lancamento.getCliente().getId());
		// Cliente novoCliente = cliente.get();
		if (cliente.isEmpty() || cliente.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}

		return lancamentoRepository.save(lancamento);
	}

}
