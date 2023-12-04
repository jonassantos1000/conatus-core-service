package br.com.app.conatus.model.response;

import java.time.ZonedDateTime;

import lombok.Builder;

@Builder
public record FornecedorResponse(
		Long id,
		String nome,
		String usuarioAtualizacao,
		ZonedDateTime dataAtualizacao
		) {

}
