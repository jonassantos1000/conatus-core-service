package br.com.app.conatus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.app.conatus.client.ViaCepClient;
import br.com.app.conatus.commons.entities.EnderecoEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.commons.enums.CodigoDominio;
import br.com.app.conatus.commons.exceptions.NaoEncontradoException;
import br.com.app.conatus.entities.factory.EnderecoEntityFactory;
import br.com.app.conatus.infra.CurrentTenantIdentifierResolverImpl;
import br.com.app.conatus.model.factory.EnderecoRecordFactory;
import br.com.app.conatus.model.response.EnderecoResponse;
import br.com.app.conatus.model.response.ViaCepResponse;
import br.com.app.conatus.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {

	private final ViaCepClient viaCepClient;
	
	private final DominioService dominioService;
	private final UsuarioService usuarioService;
	
	private final EnderecoRepository enderecoRepository;
	
	public EnderecoResponse recuperarEnderecoPorCep(String cep) {
		
		Optional<EnderecoEntity> cepDB = enderecoRepository.findByCep(cep, PageRequest.of(0, 1));
		
		if(cepDB.isPresent()) {
			return EnderecoRecordFactory.converterParaEnderecoResponse(cepDB.get());
		}
		
		return recuperarEnderecoViaCepPorCep(cep);
	}
	
	public List<EnderecoResponse> recuperarEnderecoViaCepPorFiltro(String uf, String cidade, String logradouro) {
		
		List<ViaCepResponse> listaResponseViaCep = viaCepClient.buscarEnderecoPorLogradouro(uf, cidade, logradouro);
		
		if (listaResponseViaCep.size() < 20) {	
			salvarEndereco(listaResponseViaCep);
		}
		
		return EnderecoRecordFactory.converterListaViaCepParaListaEnderecoResponse(listaResponseViaCep);
	}

	protected EnderecoEntity recuperarEnderecoPorId(Long id) {
		return enderecoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("TENANT: %s - Não foi encontrado um endereco com id: %d".formatted(CurrentTenantIdentifierResolverImpl.getCurrencyTenant(), id)));
	}
	
	protected EnderecoResponse recuperarEnderecoViaCepPorCep(String cep) {
			
		ViaCepResponse responseViaCep = viaCepClient.buscarEnderecoPorCep(cep);
		
		salvarEndereco(responseViaCep);
		
		return EnderecoRecordFactory.converterViaCepParaEnderecoResponse(responseViaCep);
	}
	
	private void salvarEndereco(List<ViaCepResponse> listaResponseViaCep) {
		listaResponseViaCep.forEach(response -> {
			
			Optional<EnderecoEntity> enderecoOptional = enderecoRepository.findByCep(response.cep(), PageRequest.of(0, 1));
			
			if(enderecoOptional.isEmpty()) {
				salvarEndereco(response);
			} 
			
		});
	}
	
	private void salvarEndereco(ViaCepResponse viaCepResponse) {
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		enderecoRepository.save(EnderecoEntityFactory.converterViaCepParaEnderecoEntity(viaCepResponse,
				dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO), usuario));
	}
	
}
