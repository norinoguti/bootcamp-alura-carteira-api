package br.com.alura.transacoes.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.dao.UsuarioDao;
import br.com.alura.transacoes.dto.UsuarioDto;
import br.com.alura.transacoes.dto.UsuarioFormDto;
import br.com.alura.transacoes.modelo.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	private ModelMapper modelMapper = new ModelMapper();

	public List<UsuarioDto> listar() {
		List<Usuario> usuarios = usuarioDao.listar();
		return usuarios.stream().map(t -> modelMapper.map(t, UsuarioDto.class)).collect(Collectors.toList());

	}

	public void cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);

		// cria senha automaticamente
		String senha = new Random().nextInt(99999) + "";
		usuario.setSenha(senha);
		//System.out.println(usuario.getSenha());
		
		usuarioDao.salvar(usuario);

	}
}
