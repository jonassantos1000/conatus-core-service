package br.com.app.conatus.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.model.request.ProdutoRequest;
import br.com.app.conatus.model.response.ProdutoResponse;
import br.com.app.conatus.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoService produtoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarProduto(@RequestBody @Valid ProdutoRequest dadosProduto) {
		produtoService.salvarProduto(dadosProduto);
	}
	
	@PutMapping("/{id}")
	public void alterarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequest dadosProduto) {
		produtoService.alterarProduto(id, dadosProduto);
	}
	
	@GetMapping("/{idProduto}")
	public ProdutoResponse pesquisarProdutosPorId(@PathVariable Long idProduto) {
		return produtoService.pesquisarProdutosPorId(idProduto);
	}
	
	@GetMapping
	public Page<ProdutoResponse> pesquisarProdutos(@PageableDefault(size = 20) Pageable page) {
		return produtoService.pesquisarProdutos(page);
	}
}
