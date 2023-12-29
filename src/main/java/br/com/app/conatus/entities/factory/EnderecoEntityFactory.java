package br.com.app.conatus.entities.factory;

import java.util.Objects;

import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.EnderecoEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.model.request.EnderecoRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnderecoEntityFactory {
	
	public static EnderecoEntity converterParaEnderecoEntity(EnderecoRequest dadosEndereco, DominioEntity situacao, UsuarioEntity usuario) {
		
		if (Objects.isNull(dadosEndereco)) {
			return null;
		}
		
		return EnderecoEntity.builder()
				.logradouro(dadosEndereco.logradouro())
				.bairro(dadosEndereco.bairro())
				.cep(dadosEndereco.cep().replaceAll("\\D", ""))
				.complemento(dadosEndereco.complemento())
				.municipio(dadosEndereco.municipio())
				.uf(dadosEndereco.uf())
				.numero(dadosEndereco.numero())
				.situacao(situacao)
				.usuarioAtualizacao(usuario)
				.tenant(usuario.getTenantSelecionado())
				.build();
	}

}
