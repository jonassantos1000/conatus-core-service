package br.com.app.conatus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.model.request.CategoriaRequest;
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

}
