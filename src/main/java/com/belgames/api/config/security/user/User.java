package com.belgames.api.config.security.user;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	@JsonIgnore
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<Integer>();
	
	public User() {
		perfis.add(3);
	};
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		perfis.add(3);
	}
	
	public Set<Perfil> getPerfis() {		
		return perfis.stream().map(x->Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil (Perfil perfil) {
		perfis.add(perfil.getCod());
	}

}
