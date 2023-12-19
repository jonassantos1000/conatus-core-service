package br.com.app.conatus.model.factory;

import br.com.app.conatus.commons.entities.ProdutoEntity;
import br.com.app.conatus.model.response.ProdutoResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoRecordFactory {
	
	public static ProdutoResponse converterParaCategoriaResponse(ProdutoEntity entity) {
		return ProdutoResponse.builder()
				.id(entity.getId())
				.descricao(entity.getDescricao())
				.fornecedor(FornecedorRecordFactory.converterParaFornecedorResponse(entity.getFornecedor()))
				.situacao(entity.getSituacao().getDescricao())
				.dataAtualizacao(entity.getDataAtualizacao())
				.categorias(ProdutoCategoriaRecordFactory.converterParaListaProdutoCategoriaResponse(entity.getCategorias()))
				.build();
	}

}
