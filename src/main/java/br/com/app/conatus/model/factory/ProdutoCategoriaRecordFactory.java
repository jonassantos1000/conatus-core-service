package br.com.app.conatus.model.factory;

import java.util.List;
import java.util.Objects;

import br.com.app.conatus.commons.entities.ProdutoCategoriaEntity;
import br.com.app.conatus.model.response.ProdutoCategoriaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoCategoriaRecordFactory {
	
	public static ProdutoCategoriaResponse converterParaProdutoCategoriaResponse(ProdutoCategoriaEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		
		return ProdutoCategoriaResponse.builder()
				.id(entity.getId())
				.idCategoria(entity.getCategoria().getId())
				.descricao(entity.getCategoria().getDescricao())
				.build();
	}
	
	public static List<ProdutoCategoriaResponse> converterParaListaProdutoCategoriaResponse(List<ProdutoCategoriaEntity> listaEntity) {
		return listaEntity.stream().map(ProdutoCategoriaRecordFactory::converterParaProdutoCategoriaResponse).toList();
	}

}
