package br.com.app.conatus.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.ClienteEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.commons.enums.CodigoDominio;
import br.com.app.conatus.commons.exceptions.NaoEncontradoException;
import br.com.app.conatus.entities.factory.ClienteEntityFactory;
import br.com.app.conatus.infra.CurrentTenantIdentifierResolverImpl;
import br.com.app.conatus.model.factory.ClienteRecordFactory;
import br.com.app.conatus.model.request.ClienteRequest;
import br.com.app.conatus.model.response.ClienteResponse;
import br.com.app.conatus.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final UsuarioService usuarioService;
	private final DominioService dominioService;
	
	private final ClienteRepository clienteRepository;
	
	@Transactional
	public void salvarCliente(ClienteRequest dadosCliente) {

		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);

		clienteRepository.save(ClienteEntityFactory.converterParaEnderecoEntity(dadosCliente,
				dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO), usuario));
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
}
