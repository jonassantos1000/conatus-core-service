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

import br.com.app.conatus.model.request.ClienteRequest;
import br.com.app.conatus.model.response.ClienteResponse;
import br.com.app.conatus.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarCategoria(@RequestBody @Valid ClienteRequest dadosCliente) {
		clienteService.salvarCliente(dadosCliente);
	}
	
	@PutMapping("/{id}")
	public void alterarCategoria(@PathVariable Long id, @RequestBody @Valid ClienteRequest dadosCliente) {
		clienteService.alterarCliente(id, dadosCliente);
	}
	
	@GetMapping("/{id}")
	public ClienteResponse pesquisarClientePorId(@PathVariable Long id) {
		return clienteService.buscarClientePorId(id);
	}
	
	@GetMapping
	public Page<ClienteResponse> pesquisarClientes(@PageableDefault(size = 20) Pageable page) {
		return clienteService.buscarCliente(page);
	}

}
