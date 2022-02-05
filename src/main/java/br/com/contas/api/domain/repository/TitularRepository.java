package br.com.contas.api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.contas.api.domain.model.Titular;

public interface TitularRepository extends JpaRepository<Titular, Long> {

	Optional<Titular> findByCpf (String cpf);
}
