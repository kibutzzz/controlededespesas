package br.inf.safetech.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.inf.safetech.daos.UsuarioDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDAO usuarioDao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/gerar-clientes").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/colaborador/**").hasRole("COLABORADOR")
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDao).passwordEncoder(new BCryptPasswordEncoder());
	}

}
