package br.com.app.conatus.services;

import static br.com.app.conatus.commons.constantes.Constante.ZONE_SP;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.CategoriaEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.commons.exceptions.NaoEncontradoException;
import br.com.app.conatus.infra.CurrentTenantIdentifierResolverImpl;
import br.com.app.conatus.model.request.CategoriaRequest;
import br.com.app.conatus.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;
	private final UsuarioService usuarioService;
	
	public void salvarCategoria(CategoriaRequest dadosCategoria) {
		
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		categoriaRepository.save(CategoriaEntity.builder()
				.descricao(dadosCategoria.descricao())
				.tenant(usuario.getTenantSelecionado())
				.usuarioAtualizacao(usuario)
				.build());
	}

	public void alterarCategoria(Long id, @Valid CategoriaRequest dadosCategoria) {
		CategoriaEntity entity = recuperarCategoriaPorId(id);
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		entity.setDataAtualizacao(ZonedDateTime.now(ZONE_SP));
		entity.setDescricao(dadosCategoria.descricao());
		entity.setUsuarioAtualizacao(usuario);
		
		categoriaRepository.save(entity);
		
	}
	
	private CategoriaEntity recuperarCategoriaPorId(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("TENANT: %s - Não foi encontrado uma categoria com id: %d".formatted(CurrentTenantIdentifierResolverImpl.getCurrencyTenant(), id)));
	}
	
}
