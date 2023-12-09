package br.com.app.conatus.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/versao")
public class VersaoController {
	
	@Value("${conatus.versao}") 
	private String versao;
	
	@GetMapping
	public Map<String, Object> consultarVersao() {
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("versao", this.versao);
		
		return response;
	}

}
