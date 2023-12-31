package br.com.app.conatus.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.ClienteEntity;
import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.EnderecoEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.commons.enums.CodigoDominio;
import br.com.app.conatus.commons.exceptions.NaoEncontradoException;
import br.com.app.conatus.entities.factory.ClienteEntityFactory;
import br.com.app.conatus.entities.factory.EnderecoEntityFactory;
import br.com.app.conatus.infra.CurrentTenantIdentifierResolverImpl;
import br.com.app.conatus.model.factory.ClienteRecordFactory;
import br.com.app.conatus.model.request.ClienteRequest;
import br.com.app.conatus.model.request.EnderecoRequest;
import br.com.app.conatus.model.response.ClienteResponse;
import br.com.app.conatus.repositories.ClienteRepository;
import br.com.app.conatus.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final UsuarioService usuarioService;
	private final DominioService dominioService;
	
	private final EnderecoRepository enderecoRepository;
	
	private final ClienteRepository clienteRepository;
	
	@Transactional
	public void salvarCliente(ClienteRequest dadosCliente) {

		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		DominioEntity situacaoAtivo = dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO);

		EnderecoEntity endereco = recuperarEnderecoCliente(dadosCliente.endereco(), situacaoAtivo, usuario);
		
		clienteRepository.save(ClienteEntityFactory.converterParaClienteEntity(dadosCliente, endereco,
				situacaoAtivo, usuario));
	}

	@Transactional
	public void alterarCliente(Long id, ClienteRequest dadosCliente) {
		
		ClienteEntity cliente = recuperarClientePorId(id);
		
		atualizarDadosCliente(cliente, dadosCliente);
		
		clienteRepository.save(cliente);
			
	}

	public ClienteResponse buscarClientePorId(Long idCliente) {

		return ClienteRecordFactory.converterParaClienteResponse(recuperarClientePorId(idCliente));
	}
	
	public Page<ClienteResponse> buscarCliente(Pageable page) {

		return clienteRepository.findAll(page).map(ClienteRecordFactory::converterParaClienteResponse);
	}
	
	private ClienteEntity recuperarClientePorId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("TENANT: %s - Não foi encontrado uma categoria com id: %d".formatted(CurrentTenantIdentifierResolverImpl.getCurrencyTenant(), id)));
	}
	
	private void atualizarDadosCliente(ClienteEntity cliente, ClienteRequest dadosCliente) {
		
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		cliente.setNome(dadosCliente.nome());
		cliente.setCelular(dadosCliente.celular());
		cliente.setEmail(dadosCliente.email());
		cliente.setTelefone(dadosCliente.telefone());
		
		salvarEnderecoCliente(cliente, dadosCliente.endereco(), usuario);
	
		clienteRepository.save(cliente);
	}

	private void salvarEnderecoCliente(ClienteEntity cliente, EnderecoRequest dadosEndereco, UsuarioEntity usuario) {
		
		if (cliente.getEndereco() != null && (dadosEndereco.cep() != cliente.getEndereco().getCep() ||
				dadosEndereco.numero() != cliente.getEndereco().getNumero())) {
			
			Optional<EnderecoEntity> enderecoOptional = enderecoRepository.findByCepAndNumero(dadosEndereco.cep(), dadosEndereco.numero());

			EnderecoEntity novoEndereco = enderecoOptional.isPresent() ? 
					enderecoOptional.get() : 
					enderecoRepository.save(EnderecoEntityFactory.converterParaEnderecoEntity(dadosEndereco, dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO), usuario));

			cliente.setEndereco(novoEndereco);
		}
	}
	
	private EnderecoEntity recuperarEnderecoCliente(EnderecoRequest enderecoRequest, DominioEntity situacao, UsuarioEntity usuario) {
		Optional<EnderecoEntity> enderecoOptional = enderecoRepository.findByCepAndNumero(enderecoRequest.cep(), enderecoRequest.numero());
		
		EnderecoEntity endereco = enderecoOptional.isEmpty() ? 
				enderecoRepository.save(EnderecoEntityFactory.converterParaEnderecoEntity(enderecoRequest, situacao, usuario)) : 
				enderecoOptional.get();

		return endereco;
	}
}
