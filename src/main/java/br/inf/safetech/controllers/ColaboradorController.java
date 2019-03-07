package br.inf.safetech.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.inf.safetech.daos.ContaDespesaDAO;
import br.inf.safetech.daos.MovimentacaoDAO;
import br.inf.safetech.daos.UsuarioDAO;
import br.inf.safetech.formwrapper.CadastroMovimentacaoWrapper;
import br.inf.safetech.formwrapper.EdicaoMovimentacaoWrapper;
import br.inf.safetech.model.CategoriaMovimentacao;
import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.EstadoConciliacao;
import br.inf.safetech.model.Movimentacao;
import br.inf.safetech.model.SituacaoConta;
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
	public ModelAndView usuarioOverview(@AuthenticationPrincipal Usuario usuarioLogado) {

		ModelAndView modelAndView = new ModelAndView("colaborador/geral");

		Usuario usuario = usuarioDao.buscarUsuarioPorId(usuarioLogado.getId());

		modelAndView.addObject("contas", contaDespesaDao.listarContaPorUsuario(usuario));

		return modelAndView;
	}

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
	public ModelAndView conta(@PathVariable("id") Integer id, @AuthenticationPrincipal Usuario usuarioLogado) {

		ModelAndView modelAndView = new ModelAndView("colaborador/conta");
		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(id);

		// faz com que o usuario não tenha acesso a uma conta que não lhe pertence
		if (conta.getUsuario().getId() != usuarioLogado.getId()) {
			return new ModelAndView("redirect:/colaborador");
		}

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
	public ModelAndView editarMovimentacao(EdicaoMovimentacaoWrapper wrapper, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView("redirect:./../conta/" + wrapper.getContaId());

		Movimentacao movimentacaoAntiga = movimentacaoDao.buscarMovimentacaoPorId(wrapper.getMovimentacao().getId());

		// não permite edição de movimentações conciliadas
		if (movimentacaoAntiga.getConciliada() == EstadoConciliacao.CONCILIADA) {
			redirectAttributes.addFlashAttribute("status", "Não é possivel editar uma movimentação conciliada");
			return modelAndView;
		}

		// não permite conciliação de movimentações sem categoria definida
		if (wrapper.getMovimentacao().getConciliada() == EstadoConciliacao.CONCILIADA
				&& wrapper.getMovimentacao().getCategoria() == CategoriaMovimentacao.INDEFINIDO) {
			redirectAttributes.addFlashAttribute("status",
					"Não é possivel conciliar uma movimentação com Categoria Indefinida");
			return modelAndView;
		}

		// não permite editar movimentações cadastradas por um admin
		if (wrapper.getMovimentacao().getCadastradoPor() == TipoUsuario.ADMIN) {
			redirectAttributes.addFlashAttribute("status",
					"Não é possivel editar uma movimentação cadastrada por um administrador");
			return modelAndView;
		}

		try {
			wrapper.getMovimentacao().setDescricao(wrapper.getMovimentacao().getDescricao());
			wrapper.getMovimentacao().setValor(wrapper.getMovimentacao().getValor());

			movimentacaoDao.mesclar(wrapper.getMovimentacao());

			redirectAttributes.addFlashAttribute("status", "Movimentação editada com sucesso");
			return modelAndView;
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("status", "Não é possivel editar a movimentação");
			return modelAndView;
		}

	}

	/**
	 * Metodo de exclusão de movimentação
	 * 
	 * @param wrapper parametro com os dados da conta e da movimentação
	 * @return redireciona para a pagina de detalhes da conta atual
	 */
	@RequestMapping(value = "movimentacao/excluir", method = RequestMethod.POST)
	public ModelAndView excluirMovimentacao(EdicaoMovimentacaoWrapper wrapper, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:./../conta/" + wrapper.getContaId());
		
		if(wrapper.getMovimentacao().getConciliada() == EstadoConciliacao.CONCILIADA) {
			redirectAttributes.addFlashAttribute("status", "Não é possivel excluir uma movimentação conciliada");
			return modelAndView;
		}
		
		if(wrapper.getMovimentacao().getCadastradoPor() == TipoUsuario.ADMIN) {
			redirectAttributes.addFlashAttribute("status", "Não é possivel excluir uma movimentação cadastrada por um administrador");
			return modelAndView;
		}
		
		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(Integer.parseInt(wrapper.getContaId()));
		Movimentacao movimentacao = movimentacaoDao.buscarMovimentacaoPorId(wrapper.getMovimentacao().getId());

		if(conta.getSituacao() == SituacaoConta.INATIVA) {
			redirectAttributes.addFlashAttribute("status", "Não é possivel excluir uma movimentação de uma conta encerrada");
			return modelAndView;
		}
		
		try {
			conta.removerMovimentacao(movimentacao);
			contaDespesaDao.mesclar(conta);
			redirectAttributes.addFlashAttribute("status", "Movimentação excluida com sucesso");
			return modelAndView;
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("status", "Não foi possivel excluir a movimentação");
			return modelAndView;
		}

	}

}
