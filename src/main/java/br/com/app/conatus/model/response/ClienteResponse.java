package br.com.app.conatus.model.response;

import java.time.ZonedDateTime;

import lombok.Builder;

@Builder
public record ClienteResponse(
		Long id,
		String nome,
		String cpf,
		String email,
		String celular,
		String telefone,
		String situacao,
		String tenant,
		String observacao,
		EnderecoResponse endereco,
		ZonedDateTime dataAtualizacao
		) {

}
