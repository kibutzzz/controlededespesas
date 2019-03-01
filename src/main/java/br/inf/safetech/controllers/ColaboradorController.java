package br.inf.safetech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
		ModelAndView modelAndView = new ModelAndView("colaborador/geral");

		Usuario usuario = usuarioDao.buscarUsuarioPorId(50);

		modelAndView.addObject("contas", contaDespesaDao.listarContaPorUsuario(usuario));

		return modelAndView;
	}

	// TODO permitir que o usuario só possa acessar contas vinculadas a ele

	@RequestMapping("conta/{id}")
	public ModelAndView conta(@PathVariable("id") Integer id) {

		ModelAndView modelAndView = new ModelAndView("colaborador/conta");
		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(id);

		modelAndView.addObject("conta", conta);

//		conta.getMovimentacoes().forEach((movimentacao) -> System.out.println(movimentacao.getValor()));

		return modelAndView;
	}

	@RequestMapping("movimentacao")
	public ModelAndView cadastrarMovimentacao(CadastroMovimentacaoWrapper wrapper) {

		wrapper.setConta(contaDespesaDao.buscarContaPeloId(wrapper.getConta().getId()));

		wrapper.getMovimentacao().setTipo(TipoMovimentacao.DEBITO);
		wrapper.getMovimentacao().setConciliada(EstadoConciliacao.NAO_CONCILIADA);
		wrapper.getMovimentacao().setCadastradoPor(TipoUsuario.COLABORADOR);
		
		movimentacaoDao.gravar(wrapper.getMovimentacao());

		wrapper.getConta().adicionarMovimentacao(wrapper.getMovimentacao());
		contaDespesaDao.mesclar(wrapper.getConta());

		return new ModelAndView("redirect:./conta/" + wrapper.getConta().getId());
	}

	@RequestMapping(value = "movimentacao/editar", method = RequestMethod.POST)
	public ModelAndView editarMovimentacao(EdicaoMovimentacaoWrapper wrapper) {

		Movimentacao movimentacao = movimentacaoDao.buscarMovimentacaoPorId(wrapper.getMovimentacao().getId());

		movimentacao.setDescricao(wrapper.getMovimentacao().getDescricao());
		movimentacao.setValor(wrapper.getMovimentacao().getValor());

		movimentacaoDao.mesclar(movimentacao);

		return new ModelAndView("redirect:./../conta/" + wrapper.getContaId());
	}

	@RequestMapping(value = "movimentacao/excluir", method = RequestMethod.POST)
	public ModelAndView excluirMovimentacao(EdicaoMovimentacaoWrapper wrapper) {

		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(Integer.parseInt(wrapper.getContaId()));
		
		Movimentacao movimentacao = movimentacaoDao.buscarMovimentacaoPorId(wrapper.getMovimentacao().getId());
		
		conta.removerMovimentacao(movimentacao);		
		contaDespesaDao.mesclar(conta);
		
		return new ModelAndView("redirect:./../conta/" + wrapper.getContaId());
	}

}
