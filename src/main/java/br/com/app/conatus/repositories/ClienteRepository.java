package br.com.app.conatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.conatus.commons.entities.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{

}
