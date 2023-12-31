package br.com.app.conatus.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.app.conatus.client.ViaCepClient;
import br.com.app.conatus.commons.entities.EnderecoEntity;
import br.com.app.conatus.commons.exceptions.NaoEncontradoException;
import br.com.app.conatus.infra.CurrentTenantIdentifierResolverImpl;
import br.com.app.conatus.model.factory.EnderecoRecordFactory;
import br.com.app.conatus.model.response.EnderecoResponse;
import br.com.app.conatus.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {

	private final ViaCepClient viaCepClient;
	
	private final EnderecoRepository enderecoRepository;
	
	public EnderecoResponse recuperarEnderecoPorCep(String cep) {
		return EnderecoRecordFactory.converterViaCepParaEnderecoResponse(viaCepClient.buscarEnderecoPorCep(cep));
	}
	
	public List<EnderecoResponse> recuperarEnderecoPorFiltro(String uf, String cidade, String logradouro) {
		return EnderecoRecordFactory.converterListaViaCepParaListaEnderecoResponse(viaCepClient.buscarEnderecoPorLogradouro(uf, cidade, logradouro));
	}
	
	protected EnderecoEntity recuperarEnderecoPorId(Long id) {
		return enderecoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("TENANT: %s - Não foi encontrado um endereco com id: %d".formatted(CurrentTenantIdentifierResolverImpl.getCurrencyTenant(), id)));
	}
}
