package br.com.app.conatus.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;

public abstract class AbstractControllerTest {

	@Autowired
	TestRestTemplate restTemplate;
	
	protected HttpHeaders getHeader() {
		HttpHeaders header = new HttpHeaders();
		header.set("tenant", "a7efdd20-e12b-48fe-81fe-0b109db5da95");
		header.setBearerAuth(UUID.randomUUID().toString());
	    return header;
	}
}
