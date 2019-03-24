package br.inf.safetech.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import br.inf.safetech.formwrapper.EncerramentoContaWrapper;
import br.inf.safetech.formwrapper.FiltroContaWrapper;
import br.inf.safetech.helper.GeradorDeDados;
import br.inf.safetech.helper.StatusInfo;
import br.inf.safetech.helper.StatusType;
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
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

		modelAndView.addObject("colaboradoresDisponiveis", (usuarioDao.listarColaboradoresAtivos()));
		modelAndView.addObject("clientesDisponiveis", clientesDao.listar());
		modelAndView.addObject("situacoes", SituacaoConta.values());

		modelAndView.addObject("ultimasContasAbertas", contaDespesaDao.buscarUltimasContasAbertas());

		return modelAndView;
	}

	@RequestMapping(value = "contas", method = RequestMethod.POST)
	public ModelAndView contasFiltradas(FiltroContaWrapper wrapper, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("admin/listaContas");
		try {
			modelAndView.addObject("contas", contaDespesaDao.buscarContaFiltrada(wrapper));

		} catch (RuntimeException e) {
			ModelAndView mav = new ModelAndView("redirect:/admin/");
			redirectAttributes.addFlashAttribute("status", new StatusInfo(StatusType.ERRO, e.getLocalizedMessage()));

			return mav;
		}

		modelAndView.addObject("colaboradoresDisponiveis", (usuarioDao.listarColaboradoresAtivos()));
		modelAndView.addObject("clientesDisponiveis", clientesDao.listar());
		modelAndView.addObject("situacoes", SituacaoConta.values());

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
//		TODO verificar se o usuario já esta cadastrado

		if (!usuario.senhasConfirmam()) {
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.ALERTA, "Os campos 'senha' e 'confirma senha' devem ser iguais"));
			return new ModelAndView("redirect:/admin/usuarios/form");
		}

		String mensagemDeStatus = null;
		StatusType tipo = null;
		try {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			usuarioDao.gravar(usuario);
			mensagemDeStatus = "Usuario: " + usuario.getNome() + " cadastrado com sucesso";
			tipo = StatusType.SUCESSO;
		} catch (RuntimeException e) {
			e.printStackTrace();
			tipo = StatusType.ERRO;
			mensagemDeStatus = "Não foi possivel gravar o Usuário";
		} finally {

			StatusInfo status = new StatusInfo(tipo, mensagemDeStatus);
			redirectAttributes.addFlashAttribute("status", status);
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

		if (clientesDisponiveis.size() == 0 || colaboradoresDisponiveis.size() == 0) {
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.ALERTA, "Cadastro de contas não é possivel devido a "
							+ "falta de clientes ou colaboradores ativos cadastrados"));

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

		conta.setDataInicio(Calendar.getInstance());
		conta.setSituacao(SituacaoConta.ATIVA);

		String statusCadastro = null;
		StatusType tipo = null;
		try {
			contaDespesaDao.gravar(conta);
			statusCadastro = "Conta gravada com sucesso";
			tipo = StatusType.SUCESSO;
		} catch (RuntimeException e) {
			e.printStackTrace();
			statusCadastro = "Não foi possivel gravar a conta";
			tipo = StatusType.ERRO;
		} finally {
			redirectAttributes.addFlashAttribute("status", new StatusInfo(tipo, statusCadastro));
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

		try {
			modelAndView.addObject("conta", contaDespesaDao.buscarContaPeloId(id));
			modelAndView.addObject("tipos", TipoMovimentacao.values());
			modelAndView.addObject("categorias", CategoriaMovimentacao.values());
			modelAndView.addObject("conciliacao", EstadoConciliacao.values());
		} catch (PersistenceException e) {
			return listarContas();
		}

		return modelAndView;
	}

	/**
	 * cadastra a movimentação passada pelo do formWrapper
	 * 
	 * @param wrapper parametro que possui informações da conta e da movimentação a
	 *                ser cadastrada
	 * @param         (injetado) redirectAttributes objeto utilizado para passar a
	 *                informação de sucesso ou falha para a proxima pagina
	 * @return redireciona para a pagina de detalhes da conta
	 */
	@RequestMapping("movimentacao")
	public ModelAndView cadastrarMovimentacao(CadastroMovimentacaoWrapper wrapper,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:./contas/" + wrapper.getConta().getId());

		wrapper.setConta(contaDespesaDao.buscarContaPeloId(wrapper.getConta().getId()));

		wrapper.getMovimentacao().setConciliada(EstadoConciliacao.NAO_CONCILIADA);
		wrapper.getMovimentacao().setCategoria(CategoriaMovimentacao.EMPRESA);
		wrapper.getMovimentacao().setCadastradoPor(TipoUsuario.ADMIN);

		if (wrapper.getConta().getSituacao() == SituacaoConta.INATIVA) {
			redirectAttributes.addFlashAttribute("status", new StatusInfo(StatusType.ERRO,
					"Não é possivel cadastrar novas movimentações por que a conta está encerrada!"));
			return modelAndView;
		}

		try {
			movimentacaoDao.gravar(wrapper.getMovimentacao());

			wrapper.getConta().adicionarMovimentacao(wrapper.getMovimentacao());
			contaDespesaDao.mesclar(wrapper.getConta());
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.SUCESSO, "Conta cadastrada com sucesso"));
			return modelAndView;
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.ERRO, "Não foi possivel realizar o cadastro da movimentação"));
			return modelAndView;
		}

	}

	/**
	 * metodo que atualiza os dados da movimentação passada como argumento através
	 * do form wrapper
	 * 
	 * @param wrapper parametro que possui informações da conta e da movimentação
	 * @param         (injetado) redirectAttributes objeto utilizado para passar a
	 *                informação de sucesso ou falha para a proxima pagina
	 * @return redireciona para a pagina de detalhes da conta
	 */
	@RequestMapping(value = "movimentacao/editar", method = RequestMethod.POST)
	public ModelAndView editarMovimentacao(EdicaoMovimentacaoWrapper wrapper, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView("redirect:./../contas/" + wrapper.getContaId());

		Movimentacao movimentacaoAntiga = movimentacaoDao.buscarMovimentacaoPorId(wrapper.getMovimentacao().getId());

		// não permite edição de movimentações conciliadas
		if (movimentacaoAntiga.getConciliada() == EstadoConciliacao.CONCILIADA) {
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.ALERTA, "Não é possivel editar uma" + " movimentação conciliada"));
			return modelAndView;
		}

		// não permite conciliação de movimentações sem categoria definida
		if (wrapper.getMovimentacao().getConciliada() == EstadoConciliacao.CONCILIADA
				&& wrapper.getMovimentacao().getCategoria() == CategoriaMovimentacao.INDEFINIDO) {
			redirectAttributes.addFlashAttribute("status", new StatusInfo(StatusType.ALERTA,
					"Não é possivel conciliar uma movimentação com Categoria Indefinida"));
			return modelAndView;
		}

		try {
			Movimentacao novaMovimentacao = movimentacaoAntiga;
			novaMovimentacao.setTipo(wrapper.getMovimentacao().getTipo());
			novaMovimentacao.setDescricao(wrapper.getMovimentacao().getDescricao());
			novaMovimentacao.setValor(wrapper.getMovimentacao().getValor());
			novaMovimentacao.setConciliada(wrapper.getMovimentacao().getConciliada());
			novaMovimentacao.setCategoria(wrapper.getMovimentacao().getCategoria());

			movimentacaoDao.mesclar(novaMovimentacao);
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.SUCESSO, "Movimentação editada com sucesso"));
			return modelAndView;
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.ERRO, "Não é possivel editar a movimentação"));
			return modelAndView;
		}

	}

	/**
	 * metodo que exclui a movimentação da conta
	 * 
	 * @param wrapper parametro que possui informações da conta e da movimentação
	 * @param         (injetado) redirectAttributes objeto utilizado para passar a
	 *                informação de sucesso ou falha para a proxima pagina
	 * @return redireciona para a pagina de detalhes da conta
	 */
	@RequestMapping(value = "movimentacao/excluir", method = RequestMethod.POST)
	public ModelAndView excluirMovimentacao(EdicaoMovimentacaoWrapper wrapper, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:./../contas/" + wrapper.getContaId());

//		TODO implementar novo metodo que busca somente as informações da conta (sem fazer fetch com as movimentações)
		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(Integer.parseInt(wrapper.getContaId()));
		Movimentacao movimentacao = movimentacaoDao.buscarMovimentacaoPorId(wrapper.getMovimentacao().getId());

		// não permite exclusão de movimentações em contas inativas
		if (conta.getSituacao() == SituacaoConta.INATIVA) {
			redirectAttributes.addFlashAttribute("status", new StatusInfo(StatusType.ALERTA,
					"Não é possivel excluir " + "movimentações de uma conta inativa"));
			return modelAndView;
		}

		// não permite exclusão de movimentações conciliadas
		if (movimentacao.getConciliada() == EstadoConciliacao.CONCILIADA) {
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.ALERTA, "Não é possivel excluir" + " uma movimentação conciliada"));
			return modelAndView;
		}

		try {
			conta.removerMovimentacao(movimentacao);
			contaDespesaDao.mesclar(conta);
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.SUCESSO, "movimentação excluida com sucesso"));
			return modelAndView;
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.ERRO, "Não é possivel excluir movimentações de uma conta excluida"));
			return modelAndView;
		}
	}

	@RequestMapping("contas/fechar/confirmacao")
	public ModelAndView confirmarEncerramento(ContaDespesa conta, RedirectAttributes redirectAttributes) {

		conta = contaDespesaDao.buscarContaPeloId(conta.getId());

		// não permite encerramento caso alguma movimentação não esteja conciliada
		if (!conta.getMovimentacoesEstaoConciliadas()) {
			redirectAttributes.addFlashAttribute("status", new StatusInfo(StatusType.ALERTA,
					"Não é possivel fechar uma conta que não possua todas as suas movimentações conciliadas"));
			return new ModelAndView("redirect:./../" + conta.getId());
		}

		ModelAndView modelAndView = new ModelAndView("admin/cadastro/encerramento");
		modelAndView.addObject("conta", conta);

		return modelAndView;
	}

	/**
	 * Metodo que encerra a conta passando a sua situação para INATIVA
	 * 
	 * 
	 * @param conta conta a ser encerrada
	 * @param       (injetado) redirectAttributes objeto utilizado para passar a
	 *              informação de sucesso ou falha para a proxima pagina
	 * @return redireciona para a pagina atual
	 */
	@RequestMapping("contas/fechar")
	public ModelAndView encerrarConta(EncerramentoContaWrapper wrapper, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:../.");

		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(wrapper.getConta().getId());

		// não permite encerramento caso alguma movimentação não esteja conciliada
		// verificação necessária ois o colaborador pode ter cadastrado uma movimentação
		// no meio tempo;
		if (!conta.getMovimentacoesEstaoConciliadas()) {
			redirectAttributes.addFlashAttribute("status", new StatusInfo(StatusType.ALERTA,
					"Não é possivel fechar uma conta que não possua todas as suas movimentações conciliadas"));
			return new ModelAndView("redirect:./" + conta.getId());
		}

		if (!(conta.getSaldoDisponivel().compareTo(new BigDecimal("0.00")) == 0)) {
			Movimentacao movimentacaoFinal = new Movimentacao();
			movimentacaoFinal.setCadastradoPor(TipoUsuario.ADMIN);
			movimentacaoFinal.setCategoria(CategoriaMovimentacao.EMPRESA);

			movimentacaoFinal.setConciliada(EstadoConciliacao.CONCILIADA);
			movimentacaoFinal.setDescricao(wrapper.getFormaDeEncerramento().toUpperCase());

			movimentacaoFinal.setTipo(TipoMovimentacao.DEBITO);
			movimentacaoFinal.setValor(conta.getSaldoDisponivel());

			movimentacaoDao.gravar(movimentacaoFinal);
			conta.adicionarMovimentacao(movimentacaoFinal);

		}

		try {
			conta.setDataFim(Calendar.getInstance());
			conta.setSituacao(SituacaoConta.INATIVA);
			contaDespesaDao.mesclar(conta);
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.SUCESSO, "Conta encerrada com sucesso"));
			return modelAndView;
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("status",
					new StatusInfo(StatusType.ERRO, "Não foi possivel fechar a conta"));
			return modelAndView;
		}
	}

	@RequestMapping("gerar-relatorio-geral")
	public ModelAndView gerarRelatorioGeral( EdicaoMovimentacaoWrapper wrapper,HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario usuarioLogado)
			throws JRException, IOException {
		
		System.out.println(wrapper.getContaId());
		
		ContaDespesa conta = contaDespesaDao.buscarContaPeloId(Integer.valueOf(wrapper.getContaId()));

		List<Map<String, ?>> datasource = new ArrayList<Map<String, ?>>();

		Map<String, Object> infoConta = new HashMap<String, Object>();

		infoConta.put("colaborador", conta.getUsuario().getNome());
		infoConta.put("cliente", conta.getCliente().getNome());
		infoConta.put("data_inicio", conta.getDataInicio());
		infoConta.put("data_fim", conta.getDataFim());
		infoConta.put("situacao", conta.getSituacao().name());
		infoConta.put("criado_por", usuarioLogado.getNome());

		datasource.add(infoConta);
		
		for(Movimentacao m : conta.getMovimentacoes()) {
			Map<String, Object> movimentacao = new HashMap<String, Object>();
			
			movimentacao.put("tipo", m.getTipo().name());
			movimentacao.put("valor", m.getValor());
			movimentacao.put("descricao", m.getDescricao());
			movimentacao.put("conciliada", m.getConciliada() == EstadoConciliacao.CONCILIADA ? "SIM": "NÃO");
			movimentacao.put("responsavel", m.getCategoria().name());
			
			datasource.add(movimentacao);
		}
		
		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(datasource);

		String pathParaRelatorio = request.getServletContext().getRealPath("/resources/relatorio/relatorio-conta.jasper");

		Map<String, Object> parametros = new HashMap<String, Object>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(pathParaRelatorio, null, jrDataSource);

		String nomeDoArquivo = "Relatorio " + conta.getCliente().getNome() + " - " + conta.getUsuario().getNome()
				+ ".pdf";

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=" + nomeDoArquivo);
		
		OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
		outStream.flush();
		outStream.close();
		
		
		return new ModelAndView("redirect: contas/"+conta.getId());
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
