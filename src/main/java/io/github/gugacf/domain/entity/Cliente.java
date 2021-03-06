package io.github.gugacf.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente") //só é usada essa anotation, caso o nome da classe seja diferente do nome da tabela no banco de dados. Nesse caso não era necessário usar a anotation
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id") //só é usada essa anotation, caso o nome da campo na classe seja diferente do nome do campo na tabela no banco de dados. Nesse caso não era necessário usar a anotation
	private Integer id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	public Cliente() {
	
	}
	
	public Cliente(String nome) {
		this.nome = nome;
	}

	public Cliente(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + "]";
	}	

}
