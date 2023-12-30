package br.com.app.conatus.model.request;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ClienteRequest(
		
		@NotBlank(message = "O nome é obrigatório")
		String nome,
		
		@CPF(message = "É necessário informar um CPF válido")
		String cpf, 
		
		@Email(message = "É necessário informar um E-mail válido")
		String email,
		
		String celular,
		
		String telefone,
		
		String observacao,
		
		@Valid
		EnderecoRequest endereco
		
		) {

}
