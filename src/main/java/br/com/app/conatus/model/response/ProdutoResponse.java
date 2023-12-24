package br.com.app.conatus.model.response;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record ProdutoResponse(
		Long id,
		String descricao,
		BigDecimal valor,
		FornecedorResponse fornecedor,
		String situacao,
		boolean flagPossuiVinculo,
		List<ProdutoCategoriaResponse> categorias,
		ZonedDateTime dataAtualizacao
		) {

}
