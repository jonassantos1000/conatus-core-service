package br.com.app.conatus.model.request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProdutoRequest(
		
		@NotBlank(message = "A descrição do produto não pode estar vazia.")
		String descricao,
		
		@NotNull(message = "O valor do produto não pode estar vazio.")
		BigDecimal valorUnitario,
		
		Long idFornecedor,
		
		List<ProdutoCategoriaRequest> categorias
		
		) {

}
