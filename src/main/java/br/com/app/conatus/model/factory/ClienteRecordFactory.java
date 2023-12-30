package br.com.app.conatus.model.factory;

import br.com.app.conatus.commons.entities.ClienteEntity;
import br.com.app.conatus.model.response.ClienteResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteRecordFactory {
	
	public static ClienteResponse converterParaClienteResponse(ClienteEntity entity) {
		return ClienteResponse.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.celular(entity.getCelular())
				.cpf(entity.getCpf())
				.email(entity.getEmail())
				.observacao(entity.getObservacao())
				.situacao(entity.getSituacao().getDescricao())
				.tenant(entity.getTenant().getCodigoTenant())
				.dataAtualizacao(entity.getDataAtualizacao())
				.endereco(EnderecoRecordFactory.converterParaEnderecoResponse(entity.getEndereco()))
				.build();
	}

}
