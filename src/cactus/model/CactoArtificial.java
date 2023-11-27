package cactus.model;

public class CactoArtificial extends Produto {

	private String material;
	
	public CactoArtificial(int id, String nome, float preco, String material) {
		super(id, nome, preco);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Material: " + this.material);
	}
}
