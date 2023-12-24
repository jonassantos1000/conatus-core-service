package br.com.app.conatus.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.conatus.commons.entities.FornecedorEntity;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long>{

	Page<FornecedorEntity> findByNomeContainingIgnoreCase(String nome, Pageable page);
}
