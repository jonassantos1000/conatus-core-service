package br.com.app.conatus.model.factory;

import br.com.app.conatus.commons.entities.CategoriaEntity;
import br.com.app.conatus.model.response.CategoriaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriaRecordFactory {
	
	public static CategoriaResponse converterParaCategoriaResponse(CategoriaEntity entity, boolean flagPossuiVinculos) {
		return CategoriaResponse.builder()
				.id(entity.getId())
				.descricao(entity.getDescricao())
				.usuarioAtualizacao(entity.getUsuarioAtualizacao().getPessoa().getNome())
				.flagPossuiVinculos(flagPossuiVinculos)
				.dataAtualizacao(entity.getDataAtualizacao())
				.build();
	}

}
