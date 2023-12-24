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

import br.com.app.conatus.model.request.FornecedorRequest;
import br.com.app.conatus.model.response.FornecedorResponse;
import br.com.app.conatus.services.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	private final FornecedorService fornecedorService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarFornecedor(@RequestBody @Valid FornecedorRequest dadosFornecedor) {
		fornecedorService.salvarFornecedor(dadosFornecedor);
	}
	
	@PutMapping("/{id}")
	public void alterarFornecedor(@PathVariable Long id, @RequestBody @Valid FornecedorRequest dadosFornecedor) {
		fornecedorService.alterarFornecedor(id, dadosFornecedor);
	}
	
	@GetMapping("/{id}")
	public FornecedorResponse pesquisarFornecedorPorId(@PathVariable Long id) {
		return fornecedorService.buscarFornecedorPorId(id);
	}
	
	@GetMapping("/nomes")
	public Page<FornecedorResponse> pesquisarFornecedoresPorNome(FornecedorRequest dadosFornecedor, @PageableDefault(size = 20) Pageable page) {
		return fornecedorService.recuperarFornecedoresPorNome(dadosFornecedor, page);
	}
	
	@GetMapping
	public Page<FornecedorResponse> pesquisarFornecedores(@PageableDefault(size = 20) Pageable page) {
		return fornecedorService.recuperarFornecedores(page);
	}

}
