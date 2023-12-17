package br.com.app.conatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.conatus.commons.entities.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{

}
