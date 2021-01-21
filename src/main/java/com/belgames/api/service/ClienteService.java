package com.belgames.api.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.belgames.api.model.Cliente;
import com.belgames.api.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente atualizar(long id, Cliente cliente) {
		Cliente clienteNovo = encontrarCliente(id);
		BeanUtils.copyProperties(cliente, clienteNovo, "id");

		return clienteRepository.save(clienteNovo);
	}

	public void atualizarStatus(Long id, @Valid Boolean status) {
		Cliente clienteSalvo = encontrarCliente(id);
		clienteSalvo.setStatus(status);
		clienteRepository.save(clienteSalvo);
	}

	/**
	 * Encontra um cliente pelo ID.
	 */
	private Cliente encontrarCliente(long id) {
		Optional<Cliente> clienteSalvo = clienteRepository.findById(id);

		if (clienteSalvo.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		Cliente clienteNovo = clienteSalvo.get();
		return clienteNovo;
	}

}
