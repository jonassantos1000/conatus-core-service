package br.com.app.conatus.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record EnderecoRequest(
		
		Long id,
		
		String logradouro,
		
		String numero,
		
		@NotBlank
		String cep,
		
		String complemento,
		
		String bairro,
		
		String municipio,
		
		String uf
		
		) {

}
