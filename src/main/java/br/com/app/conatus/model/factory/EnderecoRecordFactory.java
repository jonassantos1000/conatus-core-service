package br.com.app.conatus.model.factory;

import java.util.List;

import br.com.app.conatus.commons.entities.EnderecoEntity;
import br.com.app.conatus.model.response.EnderecoResponse;
import br.com.app.conatus.model.response.ViaCepResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnderecoRecordFactory {
	
	public static EnderecoResponse converterParaEnderecoResponse(EnderecoEntity entity) {
		return EnderecoResponse.builder()
				.id(entity.getId())
				.logradouro(entity.getLogradouro())
				.bairro(entity.getBairro())
				.cep(entity.getCep())
				.complemento(entity.getComplemento())
				.municipio(entity.getMunicipio())
				.numero(entity.getNumero())
				.uf(entity.getUf())
				.situacao(entity.getSituacao().getDescricao())
				.build();
	}

	public static EnderecoResponse converterViaCepParaEnderecoResponse(ViaCepResponse viaCep) {
		return EnderecoResponse.builder()
				.logradouro(viaCep.logradouro())
				.bairro(viaCep.bairro())
				.cep(viaCep.cep())
				.complemento(viaCep.complemento())
				.municipio(viaCep.localidade())
				.uf(viaCep.uf())
				.build();
	}
	
	public static List<EnderecoResponse> converterListaViaCepParaListaEnderecoResponse(List<ViaCepResponse> viaCep) {
		return viaCep.stream().map(EnderecoRecordFactory::converterViaCepParaEnderecoResponse).toList();
	}

}
