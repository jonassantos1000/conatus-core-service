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

import br.com.app.conatus.commons.model.response.RestPageResponse;
import br.com.app.conatus.model.request.ClienteRequest;
import br.com.app.conatus.model.request.EnderecoRequest;
import br.com.app.conatus.model.response.ClienteResponse;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class ClienteControllerTest extends AbstractControllerTest {

	private static final String RESOURCE = "/clientes";
	private static StringBuilder path = new StringBuilder();
	private static final Long ID_CLIENTE_VALIDO = 1L;
	private static final Long ID_CLIENTE_INVALIDO = 0L;
	
	
	@Test
	@Order(1)
	void esperaQueSejaRetornadoSucessoNoCadastroDeCliente() {
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarDadosClienteValido(), getHeader()), responseType);

		assertEquals(HttpStatus.CREATED, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(2)
	void esperaQueRetorneErroNoCadastroDeClienteComParametrosInvalidos() {
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarDadosClienteInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(3)
	void esperaQueRetorneSucessoNaPesquisaPeloIdCliente() {
		
		path.append("/").append(ID_CLIENTE_VALIDO);
	
		ParameterizedTypeReference<ClienteResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	
	@Test
	@Order(4)
	void esperaQueRetorneErroNaPesquisaPorIdInvalidoCliente() {
		
		path.append("/").append(ID_CLIENTE_INVALIDO);
	
		ParameterizedTypeReference<ClienteResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.NOT_FOUND, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(5)
	void esperaQueRetorneSucessoNaPesquisaPorClientePaginado() {
		
		ParameterizedTypeReference<RestPageResponse<ClienteResponse>> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(6)
	void esperaQueRetorneSucessoNaAlteracaoDoCliente() {
		
		path.append("/").append(ID_CLIENTE_VALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosClienteValido(), getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(7)
	void esperaQueRetorneErroNaAlteracaoDoClienteComIdInvalido() {
		
		path.append("/").append(ID_CLIENTE_INVALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosClienteValido(), getHeader()), responseType);

		assertEquals(HttpStatus.NOT_FOUND, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(8)
	void esperaQueRetorneErroNaAlteracaoDoClienteComDadosInvalidos() {
		
		path.append("/").append(ID_CLIENTE_VALIDO);
	
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.PUT,
				new HttpEntity<>(gerarDadosClienteInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
	}
	
		
	
	private ClienteRequest gerarDadosClienteValido() {
		return ClienteRequest.builder()
				.nome("teste junit")
				.cpf("34855661015")
				.email("teste@teste.com")
				.endereco(EnderecoRequest.builder()
						.bairro("teste")
						.cep("08025455")
						.logradouro("teste")
						.bairro("bairro teste")
						.complemento("complemento teste")
						.build())
				.build();
	}
	
	private ClienteRequest gerarDadosClienteInvalido() {
		return ClienteRequest.builder()
				.build();
	}

	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}
}
