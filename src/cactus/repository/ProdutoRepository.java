package cactus.repository;

import cactus.model.Produto;

public interface ProdutoRepository {

	// CRUD do e-commerce
	public void cadastrarProduto(Produto produto);
	public void listarTodosProdutos();
	public void consultarProduto(int id);
	public void atualizarProduto(Produto produto);
	public void deletarProduto(int id);
}
