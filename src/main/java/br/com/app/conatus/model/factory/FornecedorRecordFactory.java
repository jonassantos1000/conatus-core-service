package br.com.app.conatus.model.factory;

import br.com.app.conatus.commons.entities.FornecedorEntity;
import br.com.app.conatus.model.response.FornecedorResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FornecedorRecordFactory {
	
	public static FornecedorResponse converterParaFornecedorResponse(FornecedorEntity entity) {
		return FornecedorResponse.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.usuarioAtualizacao(entity.getUsuarioAtualizacao().getPessoa().getNome())
				.dataAtualizacao(entity.getDataAtualizacao())
				.build();
	}

}
