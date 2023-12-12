package br.com.app.conatus.services;

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
	
	public void salvarRepository(FornecedorRequest dadosFornecedor) {
		
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		fornecedorRepository.save(FornecedorEntity.builder()
				.nome(dadosFornecedor.nome())
				.tenant(usuario.getTenantSelecionado())
				.usuarioAtualizacao(usuario)
				.build());
	}

	public FornecedorResponse recuperarPorId(Long id) {
		
		return FornecedorRecordFactory
				.converterParaFornecedorResponse(buscarFornecedorPorId(id));		
	}
	
	private FornecedorEntity buscarFornecedorPorId(Long id) {
		
		return fornecedorRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("TENANT: %s - Não foi encontrado um fornecedor com id: %d".formatted(CurrentTenantIdentifierResolverImpl.getCurrencyTenant(), id)));
	}

	public Page<FornecedorResponse> recuperarFornecedores(Pageable page) {
		return fornecedorRepository.findAll(page).map(FornecedorRecordFactory::converterParaFornecedorResponse);
	}
	
}
