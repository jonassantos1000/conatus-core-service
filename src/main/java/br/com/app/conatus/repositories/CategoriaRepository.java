package br.com.app.conatus.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.conatus.commons.entities.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{
	Page<CategoriaEntity> findByDescricaoContainingIgnoreCase(String descricao, Pageable page);
}
