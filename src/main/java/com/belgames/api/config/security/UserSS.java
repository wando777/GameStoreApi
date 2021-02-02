package com.belgames.api.config.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.belgames.api.config.security.user.Perfil;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * @param id
	 * @param nome
	 * @param senha
	 * @param authorities
	 */
	public UserSS(Long id, String nome, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return nome;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
