package cactus.controller;

import java.util.ArrayList;
import java.util.Optional;

import cactus.model.*;
import cactus.repository.ProdutoRepository;
import cactus.util.Cores;

public class ProdutoController implements ProdutoRepository {
	
	private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
	int id = 0;

	@Override
	public void cadastrarProduto(Produto produto) {
		listaProdutos.add(produto);
		System.out.println(Cores.tema + "Produto cadastrado com sucesso!");		
	}

	@Override
	public void listarTodosProdutos() {
		for (var produto : listaProdutos)
			produto.visualizar();
	}

	@Override
	public void consultarProduto(int id) {
		Optional<Produto> produto = buscarNaLista(id);
		
		if(produto.isPresent()) 
			produto.get().visualizar();
		else 
			System.out.println("Produto não encontrado!");
	}

	@Override
	public void atualizarProduto(Produto produto) {
		Optional<Produto> buscaProduto = buscarNaLista(produto.getId());
		
		if(buscaProduto.isPresent()) {
			listaProdutos.set(listaProdutos.indexOf(buscaProduto.get()), produto);
		} else {
			System.out.println("Produto não encontrado!");
		}
		
	}

	@Override
	public void deletarProduto(int id) {
		Optional<Produto> produto = buscarNaLista(id);
		
		if(produto.isPresent()) {
			if(listaProdutos.remove(produto.get()) == true) {
				System.out.println("Produto deletado com sucesso!");
			}
		} else {
			System.out.println("Produto não encontrado!");
		}
		
	}

	public Optional<Produto> buscarNaLista(int id) {
		for (var produto : listaProdutos) {
			if(produto.getId() == id)
				return Optional.of(produto);
		}
		return Optional.empty();
	}
	
	public int gerarId() {
		return ++ id;
	}
	
	public int retornaTipo(int id) {
		Optional<Produto> produto = buscarNaLista(id);
		return produto.get().getTipo();
	}
}
