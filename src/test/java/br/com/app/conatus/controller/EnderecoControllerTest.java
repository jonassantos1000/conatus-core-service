package br.com.app.conatus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import br.com.app.conatus.model.response.EnderecoResponse;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class EnderecoControllerTest extends AbstractControllerTest {

	private static final String RESOURCE = "/enderecos";
	private static final String CEP_VALIDO = "02961080";
	private static final String CEP_INVALIDO = "0";
	private static StringBuilder path = new StringBuilder();

	
	@Test
	@Order(1)
	void esperaQueRetorneSucessoNaPesquisaDoEnderecoPorCep() {
		
		path.append("/").append(CEP_VALIDO);
	
		ParameterizedTypeReference<EnderecoResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	
	@Test
	@Order(2)
	void esperaQueRetorneSucessoNaPesquisaPorFiltro() {
		
		path.append("/filtros?")
			.append("&uf=").append("SP")
			.append("&municipio=").append("SAO PAULO")
			.append("&logradouro=").append("Rua joao augusto morais");
	
		ParameterizedTypeReference<List<EnderecoResponse>> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(3)
	void esperaQueRetorneErroNaPesquisaPorCepInvalido() {
		
		path.append("/").append(CEP_INVALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(4)
	void esperaQueRetorneErroNaPesquisaComFiltrosInvalidos() {
		
		path.append("/filtros?")
			.append("&uf=").append("0")
			.append("&municipio=").append("0")
			.append("&logradouro=").append("0");
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}

	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}
}
