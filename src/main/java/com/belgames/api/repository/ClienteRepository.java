package com.belgames.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belgames.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
}
