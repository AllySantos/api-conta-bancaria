package br.com.contas.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.contas.api.domain.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}
