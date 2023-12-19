package br.com.app.conatus.entities.factory;

import br.com.app.conatus.commons.entities.CategoriaEntity;
import br.com.app.conatus.commons.entities.ProdutoCategoriaEntity;
import br.com.app.conatus.commons.entities.ProdutoEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoCategoriaEntityFactory {

	public static ProdutoCategoriaEntity converterParaProdutoEntity(CategoriaEntity categoria, ProdutoEntity produto, UsuarioEntity usuario) {
		
		return ProdutoCategoriaEntity.builder()
				.categoria(categoria)
				.produto(produto)
				.usuarioAtualizacao(usuario)
				.tenant(usuario.getTenantSelecionado())
				.build();
	}
}
