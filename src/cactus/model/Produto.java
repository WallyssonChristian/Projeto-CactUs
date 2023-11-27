package cactus.model;

public abstract class Produto {

	private int id, tipo;
	private String nome;
	private float preco;
	
	final String line = ("=================================");
	
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
		System.out.println("ID do Produto: " + this.id);
		System.out.println("Tipo do Produto: " + tipo);
		System.out.println("Nome do Produto: " + this.nome);
		System.out.println("Preço do Produto: " + this.preco);
	}
}
