package br.com.app.conatus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.model.response.EnderecoResponse;
import br.com.app.conatus.services.EnderecoService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/enderecos")
public class EnderecoController {
	
	private final EnderecoService enderecoService;
	
	@GetMapping("/{cep}")
	public EnderecoResponse pesquisarEnderecoPorCep(@PathVariable String cep) {
		return enderecoService.recuperarEnderecoPorCep(cep);
	}
	
	@GetMapping("/filtros")
	public List<EnderecoResponse> pesquisarEnderecoPorFiltro(@RequestParam String uf, @RequestParam String municipio, @RequestParam String logradouro) {
		return enderecoService.recuperarEnderecoViaCepPorFiltro(uf, municipio, logradouro);
	}

}
