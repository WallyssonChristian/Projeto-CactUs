package cactus.model;

import cactus.Menu;

public abstract class Produto {

	private int id, tipo;
	private String nome;
	private float preco;
	
	String line = Menu.LINE;
	
	public Produto(int id, int tipo, String nome, float preco) {
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public void visualizar() {
		String tipo = "";
		
		switch(this.tipo) {
		case 1 -> tipo = "Natural";
		case 2 -> tipo = "Artificial";
		}
		
		System.out.println(line);
		System.out.println("	Dados do Produto	");
		System.out.println(line);
		System.out.println("ID: " + this.id);
		System.out.println("Tipo: " + tipo);
		System.out.println("Nome: " + this.nome);
		System.out.println("Preço: " + this.preco);
	}
}
