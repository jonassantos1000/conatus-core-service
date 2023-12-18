package br.com.app.conatus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import br.com.app.conatus.model.request.CategoriaRequest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class CategoriaControllerTest extends AbstractControllerTest {

	private static final String RESOURCE = "/categorias";
	private static StringBuilder path = new StringBuilder();
	private static final Long ID_CATEGORIA_VALIDO = 1L;
	private static final Long ID_CATEGORIA_INVALIDO = 0L;
	
	
	@Test
	@Order(1)
	void esperaQueSejaRetornadoSucessoNoCadastroDaCategoria() {
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarDadosCategoriaValido(), getHeader()), responseType);

		assertEquals(HttpStatus.CREATED, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(2)
	void esperaQueRetorneErroNoCadastroDoFornecedorComParametrosInvalidos() {
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarDadosCategoriaInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(3)
	void esperaQueRetorneSucessoNaAlteracaoDaCategoria() {
		
		path.append("/").append(ID_CATEGORIA_VALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosCategoriaValido(), getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(4)
	void esperaQueRetorneErroNaAlteracaoDaCategoriaComIdInvalido() {
		
		path.append("/").append(ID_CATEGORIA_INVALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosCategoriaValido(), getHeader()), responseType);

		assertEquals(HttpStatus.NOT_FOUND, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(5)
	void esperaQueRetorneErroNaAlteracaoDoFornecedorComDadosInvalidos() {
		
		path.append("/").append(ID_CATEGORIA_VALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosCategoriaInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}
	
	private CategoriaRequest gerarDadosCategoriaValido() {
		return CategoriaRequest.builder().descricao("Teste Unitario JUNIT").build();
	}
	
	private CategoriaRequest gerarDadosCategoriaInvalido() {
		return CategoriaRequest.builder().build();
	}

	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}
}
