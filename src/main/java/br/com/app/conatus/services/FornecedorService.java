package br.com.app.conatus.services;

import static br.com.app.conatus.commons.constantes.Constante.ZONE_SP;

import java.time.ZonedDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.FornecedorEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.commons.exceptions.NaoEncontradoException;
import br.com.app.conatus.infra.CurrentTenantIdentifierResolverImpl;
import br.com.app.conatus.model.factory.FornecedorRecordFactory;
import br.com.app.conatus.model.request.FornecedorRequest;
import br.com.app.conatus.model.response.FornecedorResponse;
import br.com.app.conatus.repositories.FornecedorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FornecedorService {

	private final FornecedorRepository fornecedorRepository;
	private final UsuarioService usuarioService;
	
	public void salvarFornecedor(FornecedorRequest dadosFornecedor) {
		
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		fornecedorRepository.save(FornecedorEntity.builder()
				.nome(dadosFornecedor.nome())
				.tenant(usuario.getTenantSelecionado())
				.usuarioAtualizacao(usuario)
				.build());
	}
	
	public void alterarFornecedor(Long idFornecedor, FornecedorRequest dadosFornecedor) {
		
		FornecedorEntity fornecedor = recuperarFornecedorPorId(idFornecedor);
		
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		fornecedor.setNome(dadosFornecedor.nome());
		fornecedor.setDataAtualizacao(ZonedDateTime.now(ZONE_SP));
		fornecedor.setUsuarioAtualizacao(usuario);
		
		fornecedorRepository.save(fornecedor);
		
	}

	public FornecedorResponse buscarFornecedorPorId(Long id) {
		
		FornecedorEntity fornecedor = recuperarFornecedorPorId(id);
		
		return FornecedorRecordFactory
				.converterParaFornecedorResponse(fornecedor, isPossuiVinculos(fornecedor));		
	}
	
	public Page<FornecedorResponse> recuperarFornecedores(Pageable page) {
		return fornecedorRepository.findAll(page).map(fornecedor -> {
			return FornecedorRecordFactory.converterParaFornecedorResponse(fornecedor, isPossuiVinculos(fornecedor));
		});
	}
	
	public Page<FornecedorResponse> recuperarFornecedoresPorNome(FornecedorRequest dadosFornecedor, Pageable page) {
		return fornecedorRepository.findByNomeContainingIgnoreCase(dadosFornecedor.nome(), page).map(FornecedorRecordFactory::converterParaFornecedorResponse);
	}
	
	protected FornecedorEntity recuperarFornecedorPorId(Long id) {
		
		return fornecedorRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("TENANT: %s - Não foi encontrado um fornecedor com id: %d".formatted(CurrentTenantIdentifierResolverImpl.getCurrencyTenant(), id)));
	}
	
	private boolean isPossuiVinculos(FornecedorEntity fornecedor) {
		return !fornecedor.getProdutos().isEmpty();
	}

}
