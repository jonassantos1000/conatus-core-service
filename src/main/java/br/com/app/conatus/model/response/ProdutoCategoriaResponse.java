package br.com.app.conatus.model.response;

import lombok.Builder;

@Builder
public record ProdutoCategoriaResponse(
		Long id,
		Long idCategoria,
		String descricao
		) {

}
