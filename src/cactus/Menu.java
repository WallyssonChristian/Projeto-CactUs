package cactus;

import java.io.IOException;
import java.util.Scanner;

import cactus.controller.ProdutoController;
import cactus.model.*;

public class Menu {

	final static String line = ("=================================");
	
	public static Scanner read = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ProdutoController controller = new ProdutoController();
		int op, id, tipo;
		String nome, nomeC, material, confirm;
		float preco;

		do {
			System.out.println(line);
			System.out.println("CactUs");
			System.out.println(line);
			System.out.println("1- Cadastrar Produto");
			System.out.println("2- Listar Todos os Produtos");
			System.out.println("3- Consultar Produto por ID");
			System.out.println("4- Atualizar Produto");
			System.out.println("5- Deletar Produto");
			System.out.println("0- Sair");
			System.out.println(line);
			System.out.print("Entre com a opção desejada: ");
			op = read.nextInt();
			
			switch(op) {
			case 1:
				System.out.println("\n Cadastrar Produto");
				
				System.out.print("Digite o tipo do produto(1-Natural | 2-Arficial): ");
				tipo = read.nextInt();
				
				System.out.print("Digite o nome do produto: ");
				read.skip("\\R?");
				nome = read.nextLine();
				
				System.out.print("Digite o preço do produto: ");
				preco = read.nextFloat();
				
				switch(tipo) {
					case 1 -> {
						System.out.print("Digite o nome científico: ");
						read.skip("\\R?");
						nomeC = read.nextLine();
						controller.cadastrarProduto(new CactoNatural(controller.gerarId(), tipo, nome, preco, nomeC));
					}
					case 2 -> {
						System.out.print("Digite o material do produto: ");
						read.skip("\\R?");
						material = read.nextLine();
						controller.cadastrarProduto(new CactoArtificial(controller.gerarId(), tipo, nome, preco, material));
					}
				}
				
				keyPress();
				break;
			case 2:
				System.out.println("\n Listar Todos os Produtos");
				controller.listarTodosProdutos();
				keyPress();
				break;
			case 3:
				System.out.println("\n Consultar Produto por ID");
				System.out.print("Digite o ID do produto: ");
				id = read.nextInt();
				controller.consultarProduto(id);
				keyPress();
				break;
			case 4:
				System.out.println("\n Atualizar Produto");
				
				System.out.print("Digite o ID do produto: ");
				id = read.nextInt();
				
				if (controller.buscarNaLista(id).isPresent()) {
					System.out.println("Digite os novos dados do produto: \n");
					
					System.out.println("Digite o nome do produto: ");
					read.skip("\\R?");
					nome = read.nextLine();
					
					System.out.print("Digite o preço do produto: ");
					preco = read.nextFloat();
					
					tipo = controller.retornaTipo(id);
					switch(tipo){
						case 1 -> {
							System.out.print("Digite o nome científico: ");
							read.skip("\\R?");
							nomeC = read.nextLine();
							controller.atualizarProduto(new CactoNatural(id, tipo, nome, preco, nomeC));
						}
						case 2 -> {
							System.out.print("Digite o material: ");
							read.skip("\\R?");
							material = read.nextLine();
							controller.cadastrarProduto(new CactoArtificial(id, tipo, nome, preco, material));
						}
					}
				}
				
				keyPress();
				break;
			case 5:
				System.out.println("\n Deletar Produto\n");
				System.out.print("Digite o ID do produto: ");
				id = read.nextInt();
				controller.consultarProduto(id);
				System.out.println("\nAVISO");
				System.out.println("Tem certeza que deseja deletar o produto?");
				System.out.print("Esse processo não pode ser desfeito(S/N): ");
				read.skip("\\R?");
				confirm = read.nextLine();
				if (confirm.equalsIgnoreCase("S"))
					controller.deletarProduto(id);
				else 
					System.out.println("Produto não deletado");
				keyPress();
				break;
			case 0:
				about();
				keyPress();
				break;
			default:
				System.out.println("Opção Inválida");
				
				keyPress();
				break;
			}
			
			
		} while (op != 0);
		
		
	}
	
	public static void keyPress() {
		try {
			System.out.println("\nPressione Enter para Continuar...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}
	
	public static void about() {
		System.out.println("Volte sempre, e cuidado com os espinhos !!");
		System.out.println("Projeto Desenvolvido por: Wallysson Araujo          ");
		System.out.println("wallysson.christian@outlook.com                     ");
		System.out.println("github.com/WallyssonChristian                       ");
	}

}
