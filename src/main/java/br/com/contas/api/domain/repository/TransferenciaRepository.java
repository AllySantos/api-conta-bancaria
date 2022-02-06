package br.com.contas.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.contas.api.domain.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{

}
