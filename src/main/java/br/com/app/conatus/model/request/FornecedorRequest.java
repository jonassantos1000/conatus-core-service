package br.com.app.conatus.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record FornecedorRequest(
		
		@NotBlank(message = "O nome do fornecedor não pode estar vazio.")
		String nome
		) {

}
