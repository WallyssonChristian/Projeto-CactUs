package cactus.model;

public class CactoNatural extends Produto {

	private String nomeCientifico;
	
	public CactoNatural(int id, int tipo, String nome, float preco, String nomeCientifico) {
		super(id, tipo, nome, preco);
		this.nomeCientifico = nomeCientifico;
	}

	public String getNomeCientifico() {
		return nomeCientifico;
	}

	public void setNomeCientifico(String nomeCientifico) {
		this.nomeCientifico = nomeCientifico;
	}

	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Nome Cientifico: " + this.nomeCientifico);
	}
}
