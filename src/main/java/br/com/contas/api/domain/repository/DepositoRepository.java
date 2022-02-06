package br.com.contas.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.contas.api.domain.model.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Long>{

}
