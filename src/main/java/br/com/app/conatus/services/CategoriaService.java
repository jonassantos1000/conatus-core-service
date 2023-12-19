package br.com.app.conatus.services;

import static br.com.app.conatus.commons.constantes.Constante.ZONE_SP;

import java.time.ZonedDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.CategoriaEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.commons.exceptions.NaoEncontradoException;
import br.com.app.conatus.infra.CurrentTenantIdentifierResolverImpl;
import br.com.app.conatus.model.factory.CategoriaRecordFactory;
import br.com.app.conatus.model.request.CategoriaRequest;
import br.com.app.conatus.model.response.CategoriaResponse;
import br.com.app.conatus.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;
	private final UsuarioService usuarioService;
	
	@Transactional
	public void salvarCategoria(CategoriaRequest dadosCategoria) {
		
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		categoriaRepository.save(CategoriaEntity.builder()
				.descricao(dadosCategoria.descricao())
				.tenant(usuario.getTenantSelecionado())
				.usuarioAtualizacao(usuario)
				.build());
	}

	@Transactional
	public void alterarCategoria(Long id, @Valid CategoriaRequest dadosCategoria) {
		CategoriaEntity entity = recuperarCategoriaPorId(id);
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		entity.setDataAtualizacao(ZonedDateTime.now(ZONE_SP));
		entity.setDescricao(dadosCategoria.descricao());
		entity.setUsuarioAtualizacao(usuario);
		
		categoriaRepository.save(entity);
		
	}
	
	public CategoriaResponse pesquisarCategoriaPorId(Long id) {
		return CategoriaRecordFactory.converterParaCategoriaResponse(recuperarCategoriaPorId(id));
	}
	
	public Page<CategoriaResponse> recuperarCategorias(Pageable page) {
		return categoriaRepository.findAll(page).map(CategoriaRecordFactory::converterParaCategoriaResponse);
	}
	
	protected CategoriaEntity recuperarCategoriaPorId(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("TENANT: %s - Não foi encontrado uma categoria com id: %d".formatted(CurrentTenantIdentifierResolverImpl.getCurrencyTenant(), id)));
	}


}
