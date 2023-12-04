package br.com.app.conatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.conatus.commons.entities.FornecedorEntity;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long>{

}
