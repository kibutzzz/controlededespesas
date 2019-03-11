package br.inf.safetech.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.inf.safetech.daos.UsuarioDAO;
import br.inf.safetech.model.TipoUsuario;

@EnableWebSecurity(debug=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDAO usuarioDao;

	
	/**
	 * Configuração das regras de acesso as paginas do site
	 * paginas a partir de /admin só podem ser acessadas por usuarios do tipo ADMIN
	 * paginas a partir de /colaborador só podem ser acessadas po usuários do tipo COLABORADOR
	 * paginas a partir de /estão liberadas para todos os usuários
	 * 
	 * url especial /admin/gerar-clientes está liberada para todos os usuarios pois popula o banco de dados
	 * deve ser removida na versão final
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/gerar-clientes").permitAll()
				.antMatchers("/admin/**").hasRole(TipoUsuario.ADMIN.toString())
				.antMatchers("/colaborador/**").hasRole(TipoUsuario.COLABORADOR.toString())
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", false)
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

	/**
	 * Configura a autenticação para usar a criptografia BCrypt nas senhas
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDao).passwordEncoder(new BCryptPasswordEncoder());
	}

	
	
}
