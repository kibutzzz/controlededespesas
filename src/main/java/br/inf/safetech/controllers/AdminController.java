package br.inf.safetech.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.inf.safetech.daos.ClienteDAO;
import br.inf.safetech.daos.ContaDespesaDAO;
import br.inf.safetech.daos.MovimentacaoDAO;
import br.inf.safetech.daos.UsuarioDAO;
import br.inf.safetech.formwrapper.CadastroMovimentacaoWrapper;
import br.inf.safetech.formwrapper.EdicaoMovimentacaoWrapper;
import br.inf.safetech.helper.GeradorDeDados;
import br.inf.safetech.model.CategoriaMovimentacao;
import br.inf.safetech.model.Cliente;
import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.EstadoConciliacao;
import br.inf.safetech.model.Movimentacao;
import br.inf.safetech.model.SituacaoConta;
import br.inf.safetech.model.SituacaoUsuario;
import br.inf.safetech.model.TipoMovimentacao;
import br.inf.safetech.model.TipoUsuario;
import br.inf.safetech.model.Usuario;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	private ContaDespesaDAO contaDespesaDao;
	@Autowired
	private ClienteDAO clientesDao;

	@Autowired
	private MovimentacaoDAO movimentacaoDao;

	@RequestMapping("")
	public ModelAndView adminOverview() {
		ModelAndView modelAndView = new ModelAndView("admin/geral");

		return modelAndView;
	}

	/**
	 * Path para tela de listagem de usuarios acessivel somente para usuarios que
	 * possuem a sua Role (TipoUsuario) como ADMIN
	 * 
	 * @return objeto com a lista de usuarios cadastrados no sistema
	 */
	@RequestMapping("usuarios")
	public ModelAndView listarUsuarios() {
		ModelAndView modelAndView = new ModelAndView("admin/listaUsuarios");

		List<Usuario> usuarios = usuarioDao.listar();

		modelAndView.addObject("usuarios", usuarios);

		return modelAndView;
	}

	/**
	 * Path para a tela de cadastro de novos usuarios Acessivel somente para
	 * usuarios com a Role de ADMIN
	 * 
	 * @return Objeto com as informações necessárias para o cadastro de novos
	 *         usuarios
	 */
	@RequestMapping("usuarios/form")
	public ModelAndView formularioCadastroUsuario() {
		ModelAndView modelAndView = new ModelAndView("admin/cadastro/usuario");
		modelAndView.addObject("tipos", TipoUsuario.values());
		modelAndView.addObject("situacoes", SituacaoUsuario.values());

		return modelAndView;
	}

	/**
	 * Path para o efetuar o cadastro do usuarios com os dados vindos do formulario
	 * acessivel somente para os usuarios com a Role de ADMIN acessviel somente por
	 * post
	 * 
	 * 
	 * @param usuario objeto que possui todas as informações que serão gravadas no
	 *                banco
	 * @param         (injetado) redirectAttributes objeto utilizado para passar a
	 *                informação de sucesso ou falha para a proxima pagina
	 * @return redireciona para o formulario de cadastro avisando o usuario se o
	 *         processo deu certo
	 */
	@RequestMapping(value = "usuarios/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastroUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:./");

		String mensagemDeStatus = null;
		try {
			usuarioDao.gravar(usuario);
			mensagemDeStatus = "Usuario: " + usuario.getNome() + " cadastrado com sucesso";
		} catch (RuntimeException e) {
			e.printStackTrace();
			mensagemDeStatus = "Não foi possivel gravar o Usuário";
		} finally {
			redirectAttributes.addFlashAttribute("statusCadastro", mensagemDeStatus);
		}

		return modelAndView;
	}

	/**
	 * Path para tela de listagem de contas Acessivel somente para os Usuarios com
	 * Role ADMIN
	 * 
	 * @return objeto com as contas cadastradas
	 */
	@RequestMapping("contas")
	public ModelAndView listarContas() {
		ModelAndView modelAndView = new ModelAndView("admin/listaContas");
		
		List<ContaDespesa> contas = contaDespesaDao.listar();
		
		modelAndView.addObject("contas", contas);

		return modelAndView;
	}

	/**
	 * Path para a tela de cadastro de nova Conta Acessivel somente para os usuarios
	 * com Role ADMIN
	 * 
	 * @return objeto com as informações necessarias para o cadastro de contas
	 */
	@RequestMapping("contas/form")
	public ModelAndView formularioCadastroConta(RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("admin/cadastro/conta");

		modelAndView.addObject("situacoes", SituacaoConta.values());

		List<Cliente> clientesDisponiveis = clientesDao.listar();

		modelAndView.addObject("clientesDisponiveis", clientesDisponiveis);

		List<Usuario> colaboradoresDisponiveis = usuarioDao.listarColaboradoresAtivos();

		modelAndView.addObject("colaboradoresDisponiveis", colaboradoresDisponiveis);
		
		if(clientesDisponiveis.size() == 0 || colaboradoresDisponiveis.size() == 0) {
			redirectAttributes.addFlashAttribute("infoDisponivel", "Cadastro de contas não é possivel devido a falta de clientes ou colaboradores ativos cadastrados");
		}

		return modelAndView;
	}

	/**
	 * Path para o cadastro da conta no BD Acessivel somente para os Usuarios com
	 * Role ADMIN
	 * 
	 * @param conta              objeto que possui todos os dados que serão
	 *                           cadastrados no banco
	 * @param redirectAttributes (injetado) objeto utilizado para passar a
	 *                           informação de sucesso ou falha para a proxima
	 *                           pagina
	 * @return redireciona para a pagina de cadastro de contas
	 * 
	 */

	@RequestMapping(value = "contas/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastroConta(ContaDespesa conta, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView("redirect:./");

		conta.setSituacao(SituacaoConta.ATIVA);

		String statusCadastro = null;
		try {
			contaDespesaDao.gravar(conta);
			statusCadastro = "Conta gravada com sucesso";
		} catch (RuntimeException e) {
			e.printStackTrace();
			statusCadastro = "Não foi possivel gravar a conta";
		} finally {
			redirectAttributes.addFlashAttribute("statusCadastro", statusCadastro);
		}
		
		return modelAndView;
	}

	/**
	 * Path para pagina que mostra detalhes da conta
	 * 
	 * @param id identificador da conta a ser buscada
	 * @return pagina com os dados da conta selecionada
	 */
	@RequestMapping("contas/{id}")
	public ModelAndView detalheConta(@PathVariable("id") Integer id) {

		ModelAndView modelAndView = new ModelAndView("admin/detalhe/conta");

		modelAndView.addObject("conta", contaDespesaDao.buscarContaPeloId(id));
		modelAndView.addObject("tipos", TipoMovimentacao.values());
		modelAndView.addObject("categorias", CategoriaMovimentacao.values());
		modelAndView.addObject("conciliacao", EstadoConciliacao.values());

		return modelAndView;
	}

	/**
	 * cadastra a movimentação passada pelo do formWrapper
	 * 
	 * @param wrapper parametro que possui informações da conta e da movimentação a
	 *                ser cadastrada
	 * @return redireciona para a pagina de detalhes da conta
	 */
	@RequestMapping("movimentacao")
	public ModelAndView cadastrarMovimentacao(CadastroMovimentacaoWrapper wrapper) {

		wrapper.setConta(contaDespesaDao.buscarContaPeloId(wrapper.getConta().getId()));

		wrapper.getMovimentacao().setConciliada(EstadoConciliacao.NAO_CONCILIADA);
		wrapper.getMovimentacao().setCategoria(CategoriaMovimentacao.EMPRESA);
		wrapper.getMovimentacao().setCadastradoPor(TipoUsuario.ADMIN);

		movimentacaoDao.gravar(wrapper.getMovimentacao());

		wrapper.getConta().adicionarMovimentacao(wrapper.getMovimentacao());
		contaDespesaDao.mesclar(wrapper.getConta());

		return new ModelAndView("redirect:./contas/" + wrapper.getConta().getId());
	}

	/**
	 * metodo que atualiza os dados da movimentação passada como argumento através
	 * do form wrapper
	 * 
	 * @param wrapper parametro que possui informações da conta e da movimentação
	 * @return redireciona para a pagina de detalhes da conta
	 */
	@RequestMapping(value = "movimentacao/editar", method = RequestMethod.POST)
	public ModelAndView editarMovimentacao(EdicaoMovimentacaoWrapper wrapper) {
		System.out.println(wrapper.getContaId());

//		TODO não permitir conciliação de conta caso a categoria seja INDEFINIDO
//		TODO não permitir edição caso a conta esteja INATIVA ou se a movimentação estiver conciliada

		movimentacaoDao.mesclar(wrapper.getMovimentacao());

		return new ModelAndView("redirect:./../contas/" + wrapper.getContaId());
	}

	/**
	 * metodo que exclui a movimentação da conta
	 * 
	 * @param wrapper parametro que possui informações da conta e da movimentação
	 * @return redireciona para a pagina de detalhes da conta
	 */
	@RequestMapping(value = "movimentacao/excluir", method = RequestMethod.POST)
	public ModelAndView excluirMovimentacao(EdicaoMovimentacaoWrapper wrapper) {

//		TODO validar a movimentação verificando se ela realmente pode ser excluida
		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(Integer.parseInt(wrapper.getContaId()));

		Movimentacao movimentacao = movimentacaoDao.buscarMovimentacaoPorId(wrapper.getMovimentacao().getId());

		conta.removerMovimentacao(movimentacao);
		contaDespesaDao.mesclar(conta);

		return new ModelAndView("redirect:./../contas/" + wrapper.getContaId());
	}

	/**
	 * Metodo que encerra a conta passando a sua situação para INATIVA
	 * 
	 * 
	 * @param conta conta a ser encerrada
	 * @return redireciona para a pagina atual
	 */
	@RequestMapping("contas/fechar")
	public ModelAndView encerrarConta(ContaDespesa conta) {

//		TODO validar se a conta realmente pode ser encerrada
		conta = contaDespesaDao.buscarContaPeloId(conta.getId());
		conta.setDataFim(Calendar.getInstance());
		conta.setSituacao(SituacaoConta.INATIVA);
		contaDespesaDao.mesclar(conta);

		return new ModelAndView("redirect:../.");
	}

	/**
	 * DEV MODE metodo utilizado para popular o banco
	 * 
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("gerar-clientes")
	public String gerarBaseFake() {
		GeradorDeDados gerador = new GeradorDeDados();

		clientesDao.gravar(gerador.gerarClientes());
		usuarioDao.gravar(gerador.gerarUsuarios());

		return "Clientes Criados";
	}

}
