package br.inf.safetech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.inf.safetech.daos.ContaDespesaDAO;
import br.inf.safetech.daos.MovimentacaoDAO;
import br.inf.safetech.daos.UsuarioDAO;
import br.inf.safetech.formwrapper.CadastroMovimentacaoWrapper;
import br.inf.safetech.formwrapper.EdicaoMovimentacaoWrapper;
import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.EstadoConciliacao;
import br.inf.safetech.model.Movimentacao;
import br.inf.safetech.model.TipoMovimentacao;
import br.inf.safetech.model.TipoUsuario;
import br.inf.safetech.model.Usuario;

@Controller
@RequestMapping("/colaborador")
public class ColaboradorController {

	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	private ContaDespesaDAO contaDespesaDao;
	@Autowired
	private MovimentacaoDAO movimentacaoDao;

	
	@RequestMapping("")
	public ModelAndView usuarioOverview() {
		//TODO pegar principal como parametro injetado pelo spring
		ModelAndView modelAndView = new ModelAndView("colaborador/geral");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = ((Usuario) authentication.getPrincipal());
		
		Usuario usuario = usuarioDao.buscarUsuarioPorId(usuarioLogado.getId());

		modelAndView.addObject("contas", contaDespesaDao.listarContaPorUsuario(usuario));

		return modelAndView;
	}

	// TODO permitir que o usuario só possa acessar contas vinculadas a ele
	/**
	 * metodo que busca as informações da conta selecionada
	 * 
	 * deve permitir acesso somente se o usuario logado for o mesmo usuario da conta
	 * 
	 * ver: https://www.baeldung.com/get-user-in-spring-security
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("conta/{id}")
	public ModelAndView conta(@PathVariable("id") Integer id) {

		ModelAndView modelAndView = new ModelAndView("colaborador/conta");
		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(id);

		modelAndView.addObject("conta", conta);

		return modelAndView;
	}

	/**
	 * metodo de cadastro de movimentação
	 * 
	 * 
	 * @param wrapper parametro que possui os dados da conta e da movimentação
	 * @return redireciona para a pagina de detalhes da conta atual
	 */
	@RequestMapping("movimentacao")
	public ModelAndView cadastrarMovimentacao(CadastroMovimentacaoWrapper wrapper) {

//		TODO validar os dados da movimentação
		wrapper.setConta(contaDespesaDao.buscarContaPeloId(wrapper.getConta().getId()));

		wrapper.getMovimentacao().setTipo(TipoMovimentacao.DEBITO);
		wrapper.getMovimentacao().setConciliada(EstadoConciliacao.NAO_CONCILIADA);
		wrapper.getMovimentacao().setCadastradoPor(TipoUsuario.COLABORADOR);

		movimentacaoDao.gravar(wrapper.getMovimentacao());

		wrapper.getConta().adicionarMovimentacao(wrapper.getMovimentacao());
		contaDespesaDao.mesclar(wrapper.getConta());

		return new ModelAndView("redirect:./conta/" + wrapper.getConta().getId());
	}

	/**
	 * metodo de edição de conta
	 * 
	 * @param wrapper parametro que possui os dados da conta e da movimentação
	 * @return redireciona para a pagina de detalhes da conta atual
	 */
	@RequestMapping(value = "movimentacao/editar", method = RequestMethod.POST)
	public ModelAndView editarMovimentacao(EdicaoMovimentacaoWrapper wrapper) {

//		TODO Validar os dados antes de editar
		Movimentacao movimentacao = movimentacaoDao.buscarMovimentacaoPorId(wrapper.getMovimentacao().getId());

		movimentacao.setDescricao(wrapper.getMovimentacao().getDescricao());
		movimentacao.setValor(wrapper.getMovimentacao().getValor());

		movimentacaoDao.mesclar(movimentacao);

		return new ModelAndView("redirect:./../conta/" + wrapper.getContaId());
	}

	/**
	 * Metodo de exclusão de movimentação
	 * 
	 * @param wrapper parametro com os dados da conta e da movimentação
	 * @return redireciona para a pagina de detalhes da conta atual
	 */
	@RequestMapping(value = "movimentacao/excluir", method = RequestMethod.POST)
	public ModelAndView excluirMovimentacao(EdicaoMovimentacaoWrapper wrapper) {
//		TODO validar os dados antes de excluir
		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(Integer.parseInt(wrapper.getContaId()));

		Movimentacao movimentacao = movimentacaoDao.buscarMovimentacaoPorId(wrapper.getMovimentacao().getId());

		conta.removerMovimentacao(movimentacao);
		contaDespesaDao.mesclar(conta);

		return new ModelAndView("redirect:./../conta/" + wrapper.getContaId());
	}

}
