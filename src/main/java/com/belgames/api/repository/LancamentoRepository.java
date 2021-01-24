package com.belgames.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belgames.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
