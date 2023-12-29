package br.com.app.conatus.entities.factory;

import java.util.Objects;

import br.com.app.conatus.commons.entities.ClienteEntity;
import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.model.request.ClienteRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteEntityFactory {
	
	public static ClienteEntity converterParaEnderecoEntity(ClienteRequest dadosCliente, DominioEntity situacao, UsuarioEntity usuario) {
		
		if (Objects.isNull(dadosCliente)) {
			return null;
		}
		
		return ClienteEntity.builder()
				.celular(dadosCliente.celular())
				.cpf(dadosCliente.cpf())
				.email(dadosCliente.email())
				.endereco(EnderecoEntityFactory.converterParaEnderecoEntity(dadosCliente.endereco(), situacao, usuario))
				.nome(dadosCliente.nome())
				.telefone(dadosCliente.telefone())
				.celular(dadosCliente.celular())
				.situacao(situacao)
				.usuarioAtualizacao(usuario)
				.tenant(usuario.getTenantSelecionado())
				.build();
	}

}
