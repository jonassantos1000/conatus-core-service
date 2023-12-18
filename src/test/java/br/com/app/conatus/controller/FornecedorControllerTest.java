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

import br.com.app.conatus.model.request.FornecedorRequest;
import br.com.app.conatus.model.response.FornecedorResponse;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class FornecedorControllerTest extends AbstractControllerTest {

	private static final String RESOURCE = "/fornecedores";
	private static StringBuilder path = new StringBuilder();
	private static final Long ID_FORNECEDOR_VALIDO = 1L;
	private static final Long ID_FORNECEDOR_INVALIDO = 0L;
	
	@Test
	@Order(1)
	void esperaQueSejaRetornadoSucessoNoCadastroDoFornecedor() {
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarDadosFornecedorValido(), getHeader()), responseType);

		assertEquals(HttpStatus.CREATED, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(2)
	void esperaQueRetorneErroNoCadastroDoFornecedorComParametrosInvalidos() {
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarDadosFornecedorInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(3)
	void esperaQueRetorneSucessoNaAlteracaoDoFornecedor() {
		
		path.append("/").append(ID_FORNECEDOR_VALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosFornecedorValido(), getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(4)
	void esperaQueRetorneErroNaAlteracaoDoFornecedorComIdInvalido() {
		
		path.append("/").append(ID_FORNECEDOR_INVALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosFornecedorValido(), getHeader()), responseType);

		assertEquals(HttpStatus.NOT_FOUND, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(5)
	void esperaQueRetorneErroNaAlteracaoDoFornecedorComDadosInvalidos() {
		
		path.append("/").append(ID_FORNECEDOR_VALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosFornecedorInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(6)
	void esperaQueRetorneSucessoNaPesquisaPeloIdFornecedor() {
		
		path.append("/").append(ID_FORNECEDOR_VALIDO);
	
		ParameterizedTypeReference<FornecedorResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(7)
	void esperaQueRetorneErroNaPesquisaPorIdInvalidoFornecedor() {
		
		path.append("/").append(ID_FORNECEDOR_INVALIDO);
	
		ParameterizedTypeReference<FornecedorResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.NOT_FOUND, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(8)
	void esperaQueRetorneSucessoNaPesquisaPorFornecedorPaginada() {
		
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	
	private FornecedorRequest gerarDadosFornecedorValido() {
		return FornecedorRequest.builder().nome("Teste Unitario JUNIT").build();
	}
	
	private FornecedorRequest gerarDadosFornecedorInvalido() {
		return FornecedorRequest.builder().build();
	}


	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}
}
