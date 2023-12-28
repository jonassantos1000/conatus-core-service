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

import br.com.app.conatus.model.request.CategoriaRequest;
import br.com.app.conatus.model.request.FornecedorRequest;
import br.com.app.conatus.model.response.CategoriaResponse;
import br.com.app.conatus.model.response.FornecedorResponse;
import br.com.app.conatus.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {
	
	private final CategoriaService categoriaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarCategoria(@RequestBody @Valid CategoriaRequest dadosCategoria) {
		categoriaService.salvarCategoria(dadosCategoria);
	}
	
	@PutMapping("/{id}")
	public void alterarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaRequest dadosCategoria) {
		categoriaService.alterarCategoria(id, dadosCategoria);
	}
	
	@GetMapping("/{id}")
	public CategoriaResponse buscarCategoriaPorId(@PathVariable Long id) {
		return categoriaService.pesquisarCategoriaPorId(id);
	}
	
	@GetMapping
	public Page<CategoriaResponse> pesquisarFornecedores(@PageableDefault(size = 20) Pageable page) {
		return categoriaService.recuperarCategorias(page);
	}

	@GetMapping("/descricoes")
	public Page<CategoriaResponse> pesquisarCategoriasPorDescricao(CategoriaRequest dadosCategoria, @PageableDefault(size = 20) Pageable page) {
		return categoriaService.recuperarCategoriasPorDescricacao(dadosCategoria, page);
	}

}
