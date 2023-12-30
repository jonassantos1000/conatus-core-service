package br.com.app.conatus.model.factory;

import java.util.Objects;

import br.com.app.conatus.commons.entities.FornecedorEntity;
import br.com.app.conatus.model.response.FornecedorResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FornecedorRecordFactory {
	
	public static FornecedorResponse converterParaFornecedorResponse(FornecedorEntity entity, boolean flagPossuiVinculos) {
		return FornecedorResponse.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.usuarioAtualizacao(entity.getUsuarioAtualizacao().getPessoa().getNome())
				.flagPossuiVinculos(flagPossuiVinculos)
				.dataAtualizacao(entity.getDataAtualizacao())
				.build();
	}

	public static FornecedorResponse converterParaFornecedorResponse(FornecedorEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		
		return FornecedorResponse.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.usuarioAtualizacao(entity.getUsuarioAtualizacao().getPessoa().getNome())
				.dataAtualizacao(entity.getDataAtualizacao())
				.build();
	}

}
