package br.com.app.conatus.repositories;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.conatus.commons.entities.EnderecoEntity;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

	Optional<EnderecoEntity> findByCep(String cep, PageRequest page);
	
	Optional<EnderecoEntity> findByCepAndNumero(String cep, String numero);
}
