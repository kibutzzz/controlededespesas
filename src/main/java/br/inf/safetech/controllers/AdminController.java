package br.inf.safetech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.inf.safetech.daos.ClienteDao;
import br.inf.safetech.daos.ContaDespesaDAO;
import br.inf.safetech.daos.UsuarioDAO;
import br.inf.safetech.helper.GeradorDeDados;
import br.inf.safetech.model.Cliente;
import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.SituacaoConta;
import br.inf.safetech.model.SituacaoUsuario;
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
	private ClienteDao clientesDao;

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

//		TODO logica para verificar se a inclusão foi feita com sucesso e retorno do situação para o usuario

		redirectAttributes.addFlashAttribute("statusCadastro",
				"Usuario: " + usuario.getNome() + " cadastrado com sucesso");

		usuarioDao.gravar(usuario);

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
	public ModelAndView formularioCadastroConta() {
		ModelAndView modelAndView = new ModelAndView("admin/cadastro/conta");

		modelAndView.addObject("situacoes", SituacaoConta.values());

		List<Cliente> clientesDisponiveis = clientesDao.listar();

		modelAndView.addObject("clientesDisponiveis", clientesDisponiveis);

		List<Usuario> colaboradoresDisponiveis = usuarioDao.listarColaboradoresAtivos();

		modelAndView.addObject("colaboradoresDisponiveis", colaboradoresDisponiveis);

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
	public ModelAndView cadastroConta(ContaDespesa conta,
			RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView("redirect:./");

//		TODO logica para verificar se o cadastro da conta foi efetuado com sucesso e retornar a situação ao usuario
		
		redirectAttributes.addFlashAttribute("statusCadastro", "Conta criada com sucesso");
		
		conta.setSituacao(SituacaoConta.ATIVA);
		
		contaDespesaDao.gravar(conta);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping("gerar-clientes")
	public String gerarBaseFake() {

		clientesDao.gravar(GeradorDeDados.gerarClientes());
		usuarioDao.gravar(GeradorDeDados.gerarUsuarios());

		return "Clientes Criados";
	}

}
