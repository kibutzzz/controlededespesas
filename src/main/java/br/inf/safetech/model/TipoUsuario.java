package br.inf.safetech.model;

import org.springframework.security.core.GrantedAuthority;

public enum TipoUsuario implements GrantedAuthority {
	ADMIN, COLABORADOR;

	@Override
	public String getAuthority() {

		return "ROLE_" + this.toString();
	}
}
