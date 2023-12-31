package br.com.app.conatus.entities.factory;

import java.util.Objects;

import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.EnderecoEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.model.request.EnderecoRequest;
import br.com.app.conatus.model.response.ViaCepResponse;
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

	public static EnderecoEntity converterViaCepParaEnderecoEntity(ViaCepResponse viaCep, DominioEntity situacao, UsuarioEntity usuario) {
		return EnderecoEntity.builder()
			.logradouro(viaCep.logradouro())
			.bairro(viaCep.bairro())
			.cep(viaCep.cep().replaceAll("\\D", ""))
			.complemento(viaCep.complemento())
			.municipio(viaCep.localidade())
			.uf(viaCep.uf())
			.situacao(situacao)
			.usuarioAtualizacao(usuario)
			.tenant(usuario.getTenantSelecionado())
			.build();
		
	}

}
