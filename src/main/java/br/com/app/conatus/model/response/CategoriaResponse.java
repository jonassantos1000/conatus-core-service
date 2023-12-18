package br.com.app.conatus.model.response;

import java.time.ZonedDateTime;

import lombok.Builder;

@Builder
public record CategoriaResponse(
		Long id,
		String descricao,
		String usuarioAtualizacao,
		ZonedDateTime dataAtualizacao
		) {

}
