package com.felix.agenda.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import com.felix.agenda.util.LocalDateTimeConverter;

@Entity
@Table(name = "tarefa")
public class Tarefa {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@NotBlank
	@Column(name="titulo")
	public String titulo;

	@Column(name = "is_active", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean check;
	
	@Column(name="date_check")
	@Convert(converter = LocalDateTimeConverter.class)
	public LocalDateTime dateCheck;
	
	@Column(name="lembrete")
	@Convert(converter = LocalDateTimeConverter.class)
	public LocalDateTime lembrete;

	@Column(name="descricao")
	public String descricao;

	@ManyToMany
	@JoinTable(name = "tarefa_contato", inverseJoinColumns = @JoinColumn(name = "contato_id", referencedColumnName = "id"))
	@JoinColumn(name = "tarefa_id", referencedColumnName = "id")
	public List<Contato> contatos;

	@OneToOne
	public Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getLembrete() {
		return lembrete;
	}
	
	public void setLembrete(LocalDateTime lembrete) {
		this.lembrete = lembrete;
	}
	
	public LocalDateTime getDateCheck() {
		return dateCheck;
	}

	public void setDateCheck(LocalDateTime dateCheck) {
		this.dateCheck = dateCheck;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
