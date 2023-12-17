package br.com.app.conatus.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoriaRequest(
		
		@NotBlank(message = "A descrição da categoria não pode estar vazia.")
		String descricao
		) {

}
