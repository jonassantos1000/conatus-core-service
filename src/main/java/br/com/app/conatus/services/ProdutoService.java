package br.com.app.conatus.services;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.FornecedorEntity;
import br.com.app.conatus.commons.entities.ProdutoEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.commons.enums.CodigoDominio;
import br.com.app.conatus.commons.exceptions.NaoEncontradoException;
import br.com.app.conatus.entities.factory.ProdutoCategoriaEntityFactory;
import br.com.app.conatus.entities.factory.ProdutoEntityFactory;
import br.com.app.conatus.infra.CurrentTenantIdentifierResolverImpl;
import br.com.app.conatus.model.factory.ProdutoRecordFactory;
import br.com.app.conatus.model.request.ProdutoCategoriaRequest;
import br.com.app.conatus.model.request.ProdutoRequest;
import br.com.app.conatus.model.response.ProdutoResponse;
import br.com.app.conatus.repositories.ProdutoCategoriaRepository;
import br.com.app.conatus.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final ProdutoCategoriaRepository produtoCategoriaRepository;
	
	private final FornecedorService fornecedorService;
	private final CategoriaService categoriaService;
	private final DominioService dominioService;
	private final UsuarioService usuarioService;
	
	@Transactional
	public void salvarProduto(ProdutoRequest dadosProduto) {
		
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		ProdutoEntity produto = produtoRepository.save(ProdutoEntityFactory.converterParaProdutoEntity(dadosProduto, buscarFornecedorPorId(dadosProduto.idFornecedor()),
						dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO), usuario));
				
		vincularCategorias(produto, dadosProduto.categorias(), usuario);
		
	}
	
	public ProdutoResponse pesquisarProdutosPorId(Long id) {
		
		return ProdutoRecordFactory.converterParaCategoriaResponse(recuperarProdutoPorId(id));
	}
	

	public Page<ProdutoResponse> pesquisarProdutos(Pageable page) {
		
		return produtoRepository.findAll(page).map(ProdutoRecordFactory::converterParaCategoriaResponse);
	}
	
	private void vincularCategorias(ProdutoEntity produto, List<ProdutoCategoriaRequest> dadosCategoria, UsuarioEntity usuario) {

		dadosCategoria.stream().forEach(categoria -> {

			if (Objects.nonNull(categoria.idCategoria())) {

				produtoCategoriaRepository.save(ProdutoCategoriaEntityFactory
						.converterParaProdutoEntity(categoriaService.recuperarCategoriaPorId(categoria.idCategoria()), produto, usuario));
			}
		});
	}
	
	private ProdutoEntity recuperarProdutoPorId(Long id) {
		
		return produtoRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("TENANT: %s - Não foi encontrado um produto com id: %d".formatted(CurrentTenantIdentifierResolverImpl.getCurrencyTenant(), id)));
	}
	
	private FornecedorEntity buscarFornecedorPorId(Long idFornecedor) {
		return idFornecedor != null ? fornecedorService.recuperarFornecedorPorId(idFornecedor) : null;
	}


}
