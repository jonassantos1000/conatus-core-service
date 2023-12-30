package br.com.app.conatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.conatus.commons.entities.EnderecoEntity;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

}
