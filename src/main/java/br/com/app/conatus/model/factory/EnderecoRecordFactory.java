package br.com.app.conatus.model.factory;

import br.com.app.conatus.commons.entities.EnderecoEntity;
import br.com.app.conatus.model.response.EnderecoResponse;
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

}
