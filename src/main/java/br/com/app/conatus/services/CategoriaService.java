package br.com.app.conatus.services;

import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.CategoriaEntity;
import br.com.app.conatus.commons.entities.UsuarioEntity;
import br.com.app.conatus.model.request.CategoriaRequest;
import br.com.app.conatus.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;
	private final UsuarioService usuarioService;
	
	public void salvarCategoria(CategoriaRequest dadosCategoria) {
		
		UsuarioEntity usuario = usuarioService.recuperarUsuarioPorId(1L);
		
		categoriaRepository.save(CategoriaEntity.builder()
				.descricao(dadosCategoria.descricao())
				.tenant(usuario.getTenantSelecionado())
				.usuarioAtualizacao(usuario)
				.build());
	}
	

}
