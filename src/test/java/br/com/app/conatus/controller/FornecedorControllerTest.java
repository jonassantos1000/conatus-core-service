package br.com.app.conatus.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class FornecedorControllerTest  extends AbstractControllerTest {

	private static final String RESOURCE = "/fornecedores";
	private static StringBuilder path = new StringBuilder();
	
}
