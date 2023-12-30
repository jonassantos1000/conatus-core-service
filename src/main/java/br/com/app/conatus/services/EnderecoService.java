package br.com.app.conatus.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.app.conatus.client.ViaCepClient;
import br.com.app.conatus.model.factory.EnderecoRecordFactory;
import br.com.app.conatus.model.response.EnderecoResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {

	private final ViaCepClient viaCepClient;
	
	public EnderecoResponse recuperarEnderecoPorCep(String cep) {
		return EnderecoRecordFactory.converterViaCepParaEnderecoResponse(viaCepClient.buscarEnderecoPorCep(cep));
	}
	
	public List<EnderecoResponse> recuperarEnderecoPorFiltro(String uf, String cidade, String logradouro) {
		return EnderecoRecordFactory.converterListaViaCepParaListaEnderecoResponse(viaCepClient.buscarEnderecoPorLogradouro(uf, cidade, logradouro));
	}
}
