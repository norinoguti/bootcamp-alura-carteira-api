package br.com.alura.transacoes.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.dto.AtualizacaoTransacaoFormDto;
import br.com.alura.transacoes.dto.TransacaoDetalhadaDto;
import br.com.alura.transacoes.dto.TransacaoDto;
import br.com.alura.transacoes.dto.TransacaoFormDto;
import br.com.alura.transacoes.modelo.Transacao;
import br.com.alura.transacoes.modelo.Usuario;
import br.com.alura.transacoes.repository.TransacaoRepository;
import br.com.alura.transacoes.repository.UsuarioRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Page<TransacaoDto> listar(Pageable paginacao, Usuario usuario) {
		return transacaoRepository.findAllByUsuario(paginacao, usuario)
				.map(t -> modelMapper.map(t, TransacaoDto.class));
	}

	@Transactional
	public TransacaoDto cadastrar(TransacaoFormDto dto, Usuario logado) {
		Long idUsuario = dto.getUsuarioId();

		try {
			Usuario usuario = usuarioRepository.getById(idUsuario);
			if (!usuario.equals(logado)) {
				lancarErroAcessoNegado();
			}
			Transacao transacao = modelMapper.map(dto, Transacao.class);
			transacao.setId(null);
			transacao.setUsuario(usuario);

			transacaoRepository.save(transacao);
			return modelMapper.map(transacao, TransacaoDto.class);

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Usuario inexistente");
		}

	}

	@Transactional
	public TransacaoDto atualizar(AtualizacaoTransacaoFormDto dto, Usuario logado) {
		Transacao transacao = transacaoRepository.getById(dto.getId());
		if (!transacao.pertenceAoUsuario(logado)) {
			lancarErroAcessoNegado();
		}

		transacao.atualizarInformacoes(dto.getTicker(), dto.getData(), dto.getPreco(), dto.getQuantidade(),
				dto.getTipo());

		return modelMapper.map(transacao, TransacaoDto.class);
	}

	@Transactional
	public void remover(Long id, Usuario logado) {
		Transacao transacao = transacaoRepository.getById(id);
		if(!transacao.pertenceAoUsuario(logado)) {
			lancarErroAcessoNegado();
		}
		transacaoRepository.deleteById(id);		

	}

	public TransacaoDetalhadaDto detalhar(Long id, Usuario logado) {
		Transacao transacao = transacaoRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		if(!transacao.pertenceAoUsuario(logado)) {
			lancarErroAcessoNegado();
		}
		return modelMapper.map(transacao, TransacaoDetalhadaDto.class);
	}

	private void lancarErroAcessoNegado() {
		throw new AccessDeniedException("Acesso negado!");
	}

}
