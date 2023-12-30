package br.com.app.conatus.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import br.com.app.conatus.model.response.ViaCepResponse;

@Service
public class ViaCepClient extends WebClientGenerico{
	
	@Value("${viacep.url}")
	private String URL_VIA_CEP;

    public ViaCepResponse buscarEnderecoPorCep(String cep) {
    	
    	String path = new StringBuilder().append(cep).append("/json/").toString();
    	
		return super.get(URL_VIA_CEP.concat(path), ViaCepResponse.class);
	}
    
    public List<ViaCepResponse> buscarEnderecoPorLogradouro(String uf, String cidade, String logradouro){
        	
    	String path = new StringBuilder().append(uf)
    							.append("/").append(cidade)
    							.append("/").append(logradouro)
    							.append("/json/")
    							.toString();
    	
		return super.getList(URL_VIA_CEP.concat(path), new ParameterizedTypeReference<List<ViaCepResponse>>() {});
    }
    
}
