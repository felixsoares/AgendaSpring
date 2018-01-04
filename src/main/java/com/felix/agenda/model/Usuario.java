package com.felix.agenda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@NotBlank
	public String nome;
	
	@NotNull
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean ativo;
	
	@NotBlank
	public String username;
	
	@NotBlank
	public String senha;
	
	@Transient
	public boolean necessarioEncriptarSenha = true;
	
	public boolean isNecessarioEncriptarSenha() {
		return necessarioEncriptarSenha;
	}
	
	public void setNecessarioEncriptarSenha(boolean necessarioEncriptarSenha) {
		this.necessarioEncriptarSenha = necessarioEncriptarSenha;
	}

	public String getNome() {
		return nome;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
