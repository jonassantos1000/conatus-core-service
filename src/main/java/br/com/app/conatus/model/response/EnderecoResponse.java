package br.com.app.conatus.model.response;

import lombok.Builder;

@Builder
public record EnderecoResponse(
		Long id,
		
		String logradouro,
		
		String numero,

		String cep,
		
		String complemento,
		
		String bairro,
		
		String municipio,
		
		String uf,
		
		String situacao
		) {

}
