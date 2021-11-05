package br.com.alura.transacoes.service;

import java.util.Random;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.dto.AtualizacaoUsuarioFormDto;
import br.com.alura.transacoes.dto.UsuarioDto;
import br.com.alura.transacoes.dto.UsuarioFormDto;
import br.com.alura.transacoes.modelo.Perfil;
import br.com.alura.transacoes.modelo.Usuario;
import br.com.alura.transacoes.repository.PerfilRepository;
import br.com.alura.transacoes.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PerfilRepository perfilRepository;

	public Page<UsuarioDto> listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(t -> modelMapper.map(t, UsuarioDto.class));

	}

	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		usuario.setId(null);
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		usuario.adicionarPerfil(perfil);
		// cria senha automaticamente
		String senha = new Random().nextInt(99999) + "";
		System.out.println(usuario.getSenha());
		usuario.setSenha(bCryptPasswordEncoder.encode(senha));
		//System.out.println(usuario.getSenha());
		
		usuarioRepository.save(usuario);
		
		return modelMapper.map(usuario, UsuarioDto.class);
	}
	
	@Transactional
	public UsuarioDto atualizar(AtualizacaoUsuarioFormDto dto) {
		Usuario usuario = usuarioRepository.getById(dto.getId());		
		usuario.atualizarInformacoes(dto.getLogin(),dto.getNome());		
		return modelMapper.map(usuario, UsuarioDto.class);
	}
	
	@Transactional
	public void remover(Long id) {
		try{
			usuarioRepository.deleteById(id);
			usuarioRepository.flush();
		}catch(org.springframework.dao.DataIntegrityViolationException e) {
			throw new RuntimeException("Existe transação vinculada a esse usuário, exclua a transação primeiro");
		}
		
		
	}
	
	public UsuarioDto detalhar(Long id) {
		Usuario usuario = usuarioRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
				return modelMapper.map(usuario, UsuarioDto.class);
	}
}
