package br.com.app.conatus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
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

import br.com.app.conatus.commons.model.response.RestPageResponse;
import br.com.app.conatus.model.request.ProdutoCategoriaRequest;
import br.com.app.conatus.model.request.ProdutoRequest;
import br.com.app.conatus.model.response.FornecedorResponse;
import br.com.app.conatus.model.response.ProdutoResponse;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class ProdutoControllerTest extends AbstractControllerTest {

	private static final String RESOURCE = "/produtos";
	private static StringBuilder path = new StringBuilder();
	private static final Long ID_PRODUTO_VALIDO = 1L;
	private static final Long ID_PRODUTO_INVALIDO = 0L;
	
	
	@Test
	@Order(1)
	void esperaQueSejaRetornadoSucessoNoCadastroDeProduto() {
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarDadosProdutoValido(), getHeader()), responseType);

		assertEquals(HttpStatus.CREATED, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(2)
	void esperaQueRetorneErroNoCadastroDeProdutoComParametrosInvalidos() {
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarDadosProdutoInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(3)
	void esperaQueRetorneSucessoNaPesquisaPeloIdProduto() {
		
		path.append("/").append(ID_PRODUTO_VALIDO);
	
		ParameterizedTypeReference<FornecedorResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	
	@Test
	@Order(4)
	void esperaQueRetorneErroNaPesquisaPorIdInvalidoProduto() {
		
		path.append("/").append(ID_PRODUTO_INVALIDO);
	
		ParameterizedTypeReference<FornecedorResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.NOT_FOUND, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(5)
	void esperaQueRetorneSucessoNaPesquisaPorProdutoPaginado() {
		
		ParameterizedTypeReference<RestPageResponse<ProdutoResponse>> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(6)
	void esperaQueRetorneSucessoNaAlteracaoDoFornecedor() {
		
		path.append("/").append(ID_PRODUTO_VALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosProdutoValido(), getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(7)
	void esperaQueRetorneErroNaAlteracaoDoFornecedorComIdInvalido() {
		
		path.append("/").append(ID_PRODUTO_INVALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosProdutoValido(), getHeader()), responseType);

		assertEquals(HttpStatus.NOT_FOUND, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(8)
	void esperaQueRetorneErroNaAlteracaoDoFornecedorComDadosInvalidos() {
		
		path.append("/").append(ID_PRODUTO_VALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosProdutoInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}
	
		
	
	private ProdutoRequest gerarDadosProdutoValido() {
		return ProdutoRequest.builder()
				.descricao("Teste Unitario JUNIT")
				.idFornecedor(999L)
				.valorUnitario(BigDecimal.TEN)
				.categorias(
						List.of(ProdutoCategoriaRequest.builder()
								.idCategoria(999L).build()
								))
				.build();
	}
	
	private ProdutoRequest gerarDadosProdutoInvalido() {
		return ProdutoRequest.builder()
				.build();
	}

	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}
}
