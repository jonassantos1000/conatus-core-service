package br.com.app.conatus.entities.factory;

import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.FornecedorEntity;
import br.com.app.conatus.commons.entities.ProdutoEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.model.request.ProdutoRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoEntityFactory {
	
	public static ProdutoEntity converterParaProdutoEntity(ProdutoRequest dadosProduto, FornecedorEntity fornecedor, DominioEntity situacao, UsuarioEntity usuario) {
		
		return ProdutoEntity.builder()
				.descricao(dadosProduto.descricao())
				.valorUnitario(dadosProduto.valorUnitario())
				.fornecedor(fornecedor)
				.situacao(situacao)
				.usuarioAtualizacao(usuario)
				.tenant(usuario.getTenantSelecionado())
				.build();
	}

}
