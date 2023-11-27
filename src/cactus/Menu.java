package cactus;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import cactus.controller.ProdutoController;
import cactus.model.*;
import cactus.util.Cores;

public class Menu {

	public final static String LINE = (Cores.tema2 + "=================================" + Cores.tema);
	
	public static Scanner read = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ProdutoController controller = new ProdutoController();
		int op, id, tipo;
		String nome, nomeC, material, confirm;
		float preco;
		
		CactoNatural cn1 = new CactoNatural(controller.gerarId(), 1, "Cacto-Espinho", 6000, "Ferocactus latispinus");
		controller.cadastrarProduto(cn1);
		CactoNatural cn2 = new CactoNatural(controller.gerarId(), 1, "Cacto-Orquídea", 300, "Epiphyllum ackermannii");
		controller.cadastrarProduto(cn2);
		CactoArtificial ca1 = new CactoArtificial(controller.gerarId(), 2, "Cacto de Porcelana", 80, "Porcelana");
		controller.cadastrarProduto(ca1);
		CactoArtificial ca2 = new CactoArtificial(controller.gerarId(), 2, "Cacto de Pelúcia", 40, "Pelúcia");
		controller.cadastrarProduto(ca2);
		
		do {
			System.out.println(Cores.tema);
			System.out.println(LINE);
			System.out.println("||                             ||");
			System.out.println(" ||        🌵 CactUs 🌵        ||");
			System.out.println("||                             ||");
			System.out.println(LINE);
			System.out.println("1- Cadastrar Produto");
			System.out.println("2- Listar Todos os Produtos");
			System.out.println("3- Consultar Produto por ID");
			System.out.println("4- Atualizar Produto");
			System.out.println("5- Deletar Produto");
			System.out.println("0- Sair");
			System.out.println(LINE);
			System.out.print("Entre com a opção desejada: ");
			
			try {
				op = read.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(Cores.error + "\nDigite valores inteiros!\n" +Cores.tema);
				read.nextLine();
				op = -1;
			}
			
			
			switch(op) {
			case 1:
				System.out.println("\n🌵 Cadastrar Produto");
				
				do {
					tipo = validaInt("Tipo do Cacto(1-Natural | 2-Arficial): ");
					if (tipo < 1 || tipo > 2)
						System.out.println(Cores.error + "Valor Inválido!" + Cores.tema);
				} while (tipo < 1 || tipo > 2);
				
				do {
					nome = validaString("Nome do Cacto: ");
					if(nome.equals("-1")) {
						System.out.println(Cores.error + "Nome Inválido" + Cores.tema);
					}
				} while (nome.equals("-1"));
					
				do {
					preco = validaValor("Preço do Cacto: ");
					if (preco < 0) System.out.println("O valor não pode ser negativo!");
				} while (preco < 0);
				
				switch(tipo) {
					case 1 -> {
						System.out.print("Nome científico: ");
						read.skip("\\R?");
						nomeC = read.nextLine();
						controller.cadastrarProduto(new CactoNatural(controller.gerarId(), tipo, nome, preco, nomeC));
					}
					case 2 -> {
						System.out.print("Material: ");
						read.skip("\\R?");
						material = read.nextLine();
						controller.cadastrarProduto(new CactoArtificial(controller.gerarId(), tipo, nome, preco, material));
					}
				}
				
				keyPress();
				break;
			case 2:
				System.out.println("\n🌵 Listar Todos os Produtos");
				controller.listarTodosProdutos();
				keyPress();
				break;
			case 3:
				System.out.println("\n🌵 Consultar Produto por ID");
				System.out.print("Digite o ID do produto: ");
				id = read.nextInt();
				controller.consultarProduto(id);
				keyPress();
				break;
			case 4:
				System.out.println("\n🌵 Atualizar Produto");
				
				id = validaInt("Digite o ID do produto: ");
				
				if (controller.buscarNaLista(id).isPresent()) {
					System.out.println("Digite os novos dados do produto: \n");
					
					do {
						nome = validaString("Nome do Cacto: ");
						if(nome.equals("-1")) {
							System.out.println(Cores.error + "Nome Inválido" + Cores.tema);
						}
					} while (nome.equals("-1"));
					
					do {
						preco = validaValor("Preço do Cacto: ");
						if (preco < 0) System.out.println("O valor não pode ser negativo!");
					} while (preco < 0);
					
					tipo = controller.retornaTipo(id);
					switch(tipo){
						case 1 -> {
							System.out.print("Nome científico: ");
							read.skip("\\R?");
							nomeC = read.nextLine();
							controller.atualizarProduto(new CactoNatural(id, tipo, nome, preco, nomeC));
						}
						case 2 -> {
							System.out.print("Material: ");
							read.skip("\\R?");
							material = read.nextLine();
							controller.cadastrarProduto(new CactoArtificial(id, tipo, nome, preco, material));
						}
					}
				} else {
					System.out.println("\nConta não encontrada!");
				}
				
				keyPress();
				break;
			case 5:
				System.out.println("\n🌵 Deletar Produto\n");
				System.out.print("Digite o ID do produto: ");
				id = read.nextInt();
				controller.consultarProduto(id);
				System.out.println(Cores.error + "\nAVISO" + Cores.tema);
				System.out.println("Tem certeza que deseja deletar o produto?");
				System.out.print("Esse processo não pode ser desfeito(S/N): ");
				read.skip("\\R?");
				confirm = read.nextLine();
				if (confirm.equalsIgnoreCase("S"))
					controller.deletarProduto(id);
				else 
					System.out.println("\nProduto não deletado");
				keyPress();
				break;
			case 0:
				about();
				break;
			default:
				System.out.println("\nOpção Inválida");
				
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
		System.out.println("\n\n            -||||||||-         ");
		System.out.println("            -||    ||-         ");
		System.out.println("            -||    ||- ||||||- ");
		System.out.println("            -||    ||- ||  ||- ");
		System.out.println("            -||    ||||||  ||- ");
		System.out.println("    -|||||| -||            ||- ");
		System.out.println("    -||  ||||||    ||||||||||- ");
		System.out.println("    -||            ||-         ");
		System.out.println("    -||||||||||    ||-         ");
		System.out.println("            -||    ||-         ");
		System.out.println("            -||    ||-         ");
		System.out.println("            -||    ||-         ");
		System.out.println(LINE);
		System.out.println("Volte sempre, e cuidado com os espinhos !!");
		System.out.println("Projeto Desenvolvido por: Wallysson Araujo          ");
		System.out.println("wallysson.christian@outlook.com                     ");
		System.out.println("github.com/WallyssonChristian                       ");
	}
	
	public static void apenasLetras(String input) {
		if (!input.matches("^[\\p{L}\\s\\-]+$")) {
			throw new InputMismatchException("O nome não deve conter números.");
		}
	}
	
	public static String validaString(String texto) {
		String nome;
		try {
			System.out.print(texto);
			read.skip("\\R?");
			nome = read.nextLine();
			apenasLetras(nome);
			return nome;
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			return "-1";
		}
	}
	
	public static int validaInt(String texto) {
		try {
			System.out.print(texto);
			return read.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Deve conter apenas Números!");
			read.next();
			return -1;
		}
	}
	
	public static float validaValor(String texto) {
		System.out.print(texto);
		while(!read.hasNextFloat()) {
			System.out.println("Por favor, digite um valor válido.");
			System.out.print(texto);
			read.next();
		}
		return read.nextFloat();
	}

}
