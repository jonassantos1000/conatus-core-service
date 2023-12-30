package br.com.app.conatus.client;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.com.app.conatus.commons.exceptions.MsgException;
import br.com.app.conatus.commons.exceptions.NaoEncontradoException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebClientGenerico {

	private final WebClient webClient;
		
	@Value("${webclient.timeout.seconds}")
	private Integer timeout;
	
	public WebClientGenerico() {
		this.webClient = WebClient.builder().build();
	}
	
    public <T> T get(String endpoint, Class<T> responseType) {
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(responseType)
                .timeout(Duration.ofSeconds(timeout))
                .onErrorMap(WebClientResponseException.class, this::handleWebClientResponseException)
                .block();
    }
    
    public <T> List<T> getList(String endpoint, ParameterizedTypeReference<List<T>> typeReference) {
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(typeReference)
                .timeout(Duration.ofSeconds(timeout))
                .onErrorMap(WebClientResponseException.class, this::handleWebClientResponseException)
                .block();
    }
    
    private RuntimeException handleWebClientResponseException(WebClientResponseException ex) {
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new NaoEncontradoException("Recurso não encontrado");
        }
        
        if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return new MsgException("Solicitação inválida");
        }
        
        return new MsgException("Ocorreu um erro ao consultar CEP");
    }
}
