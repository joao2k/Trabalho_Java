package Venda_Croche;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class Venda_croche {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
       // System.out.print("Digite uma data no formato YYYY-MM-DD: ");
        //String dataInput = scanner.nextLine();
		Cliente cliente = new Cliente("","",LocalDate.parse("1900-01-01"),"");
		Produto produto=new Produto("","");
		Vendas venda= new Vendas(0,0,0,false,0,null,null);
		
		//para criar Cliente
		 String cpf;
		 String nome;
		 String data_nascimento;
		 String endereco;
		 int id;
		 //para criar Produto
		 String cor;
		 String tipo;
		 //para venda
		 float valor;
		 boolean entrega_domicilio = false;
		 int status;
		 String data_entrega;
		 String observacao;
		 String aux;
		int op = 0;
		do {
			System.out.println("1-cadastrar Cliente.");
			System.out.println("2-cadastrar produto.");
			System.out.println("3-listar produtos.");
			System.out.println("4-listar cliente.");
			System.out.println("5-remover produto");
			System.out.println("6-remover cliente");
			System.out.println("7-alterar produto");
			System.out.println("8-alterar cliente");
			System.out.println("9-Começar compra");
			System.out.println("10-alterar status compra");
			System.out.println("11-deletar compra");
			System.out.println("12-listar compra");
			System.out.println("13-sair");
			op = scanner.nextInt();
			scanner.nextLine();
			switch(op) {
				case 1:
					System.out.println("Digite o CPF:");
					cpf = scanner.nextLine();
					System.out.println("Digite o Nome completo:");
					nome = scanner.nextLine();
					System.out.println("Digite o Endereço completo:");
					endereco = scanner.nextLine();
					System.out.println("Digite o Data de Nascimento no formato YYYY-MM-DD:");
					data_nascimento = scanner.nextLine();
					cliente=new Cliente(cpf,nome,LocalDate.parse(data_nascimento),endereco);
					if (cliente.insert_banco()!="erro") {
						System.out.println("Cliente:"+cliente.getID());
						System.out.println("Cliente Cadastrado com Sucesso:");
					}else {
						System.out.println("Cliente Não Cadastrado com Sucesso:");
					}
					
					break;
				case 2:
					System.out.println("Digite o Tipo de tecido:");
					tipo = scanner.nextLine();
					System.out.println("Digite o Nome da cor:");
					cor = scanner.nextLine();
					
					produto=new Produto(cor,tipo);
					if (produto.insert_banco()!="erro") {
						System.out.println("O ID de Produto é:"+produto.getId());
						System.out.println("Produto Cadastrado com Sucesso:");
					}else {
						System.out.println("Produto Não Cadastrado com Sucesso:");
					}
					
					break;
				case 3:
					List<Produto> produtos = new ArrayList<>();
					produtos=produto.listar_produtos();
					for(int i=0;i<produtos.size();i++) {
						System.out.println(produtos.get(i).toString());						
					}
					break;
				case 4:
					List<Cliente> clientes = new ArrayList<>();
					clientes=cliente.listar_clientes();
					for(int i=0;i<clientes.size();i++) {
						System.out.println(clientes.get(i).toString());						
					}
					break;
				case 5:
					System.out.println("O Id do produto que vc deseja DELETAR:");
					id =scanner.nextInt();
					if( produto.procurar_produto(id)!=null) {
						if (produto.delete_banco()!="erro") {
							System.out.println("Produto Deletado com Sucesso:");
						}else {
							System.out.println("Produto Não Deletado com Sucesso:");
						}
					}else {
						System.out.println("Produto Não Deletado com Sucesso:");
					}
					
					break;
				case 6:
					System.out.println("O Id do cliente que vc deseja DELETAR:");
					id =scanner.nextInt();
					if( cliente.procurar_cliente(id)!=null) {
						if (cliente.delete_banco()!="erro") {
							System.out.println("Cliente Deletado com Sucesso:");
						}else {
							System.out.println("Cliente Não Deletado com Sucesso:");
						}
					}else {
						System.out.println("Cliente Não Deletado com Sucesso:");
					}
					
					break;
				case 7:
					System.out.println("O Id do Produto que vc deseja alterar:");
					id =scanner.nextInt();
					if( produto.procurar_produto(id)!=null) {
						System.out.println("Digite o Tipo de tecido:");
						produto.setTipo(scanner.nextLine());
						produto.setTipo(scanner.nextLine());
						System.out.println("Digite o Nome da cor:");
						produto.setCor(scanner.nextLine());
						if (produto.update_banco()!="erro") {
							System.out.println("Produto Alterado com Sucesso:");
						}else {
							System.out.println("Produto Não Alterado com Sucesso:");
						}
					}else {
						System.out.println("Produto Não Alterado com Sucesso:");
					}
					
					
					break;
				case 8:
					System.out.println("O Id do cliente que vc deseja alterar:");
					id =scanner.nextInt();
					if( cliente.procurar_cliente(id)!=null) {
						System.out.println("Digite o CPF:");
						cliente.setCpf(scanner.nextLine());
						cliente.setCpf(scanner.nextLine());
						System.out.println("Digite o Nome completo:");
						cliente.setNome(scanner.nextLine());
						System.out.println("Digite o Endereço completo:");
						cliente.setEndereco(scanner.nextLine());
						System.out.println("Digite o Data de Nascimento no formato YYYY-MM-DD:");
						data_nascimento = scanner.nextLine();
						cliente.setData_nascimento(LocalDate.parse(data_nascimento));
						if (cliente.update_banco()!="erro") {
							System.out.println("Cliente Alterado com Sucesso:");
						}else {
							System.out.println("Cliente Não Alterado com Sucesso:");
						}
					}else {
						System.out.println("Cliente Não Alterado com Sucesso:");
					}
					
					break;

				case 9:
					do {
						System.out.println("Digite o Id do Cliente:");
						id = scanner.nextInt();
						if(cliente.procurar_cliente(id)==null) {
							System.out.println("Valor inválido");
						}
					}while(cliente.procurar_cliente(id)==null);
					do {
						System.out.println("Digite o Id do Produto:");
						id = scanner.nextInt();
						if(produto.procurar_produto(id)==null) {
							System.out.println("Valor inválido");
						}
					}while(produto.procurar_produto(id)==null);
					System.out.println("Digite o Valor:");
					valor = scanner.nextFloat();
					System.out.println("Digite a Observação:");
					observacao = scanner.nextLine();
					observacao = scanner.nextLine();
					do {
				        System.out.println("Vai ter entrega(S/N):");
				        aux = scanner.nextLine();
				        
				        if (aux.equalsIgnoreCase("N")) {
				            entrega_domicilio = false;
				            aux = "";  
				        }else if (aux.equalsIgnoreCase("S")) {
				            entrega_domicilio = true;
				            aux = "";  
				        } else {
				            aux = "erro";
				            System.out.println("Valor inválido");
				        }
					}while (aux.equals("erro"));
	

					if(entrega_domicilio==true) {
						System.out.println("Digite o Data da Entrega no formato YYYY-MM-DD:");
						data_entrega = scanner.nextLine();
					}else {
						data_entrega="0000-01-01";
					}
					if(data_entrega=="0000-01-01") {
						venda=new Vendas(cliente.getID(),produto.getId(),valor,entrega_domicilio,1,null,observacao);
					}else {
						venda=new Vendas(cliente.getID(),produto.getId(),valor,entrega_domicilio,1,LocalDate.parse(data_entrega),observacao);
					}
					if (venda.insert_banco()!="erro") {
						System.out.println("Venda:"+venda.getId());
						System.out.println("Venda Cadastrado com Sucesso:");
					}else {
						System.out.println("Venda Não Cadastrado com Sucesso:");
					}
					break;
				case 10:
					System.out.println("O Id da Venda que vc deseja alterar:");
					id =scanner.nextInt();
					if( venda.procurar_venda(id)!=null) {
						do {
							System.out.println("Digite o Id do Cliente:");
							id = scanner.nextInt();
							venda.setId_cliente(id);
							if(cliente.procurar_cliente(id)==null) {
								System.out.println("Valor inválido");
							}
						}while(cliente.procurar_cliente(id)==null);
						do {
							System.out.println("Digite o Id do Produto:");
							id = scanner.nextInt();
							venda.setId_produto(id);
							if(produto.procurar_produto(id)==null) {
								System.out.println("Valor inválido");
							}
						}while(produto.procurar_produto(id)==null);
						System.out.println("Digite o Valor:");
						valor = scanner.nextFloat();
						venda.setValor(valor);
						System.out.println("Digite a Observação:");
						observacao = scanner.nextLine();
						observacao = scanner.nextLine();
						venda.setOberservacao(observacao);
						
						
						do {
					        System.out.println("Vai ter entrega(S/N):");
					        aux = scanner.nextLine();
					        
					        if (aux.equalsIgnoreCase("N")) {
					            entrega_domicilio = false;
					            aux = "";  
					        }else if (aux.equalsIgnoreCase("S")) {
					            entrega_domicilio = true;
					            aux = "";  
					        } else {
					            aux = "erro";
					            System.out.println("Valor inválido");
					        }
						}while (aux.equals("erro"));
						if(entrega_domicilio==true) {
							System.out.println("Digite o Data da Entrega no formato YYYY-MM-DD:");
							data_entrega = scanner.nextLine();
							venda.setData_entrega(LocalDate.parse(data_entrega));
							venda.setEntrega_domicilio(true);
						}else {
							venda.setEntrega_domicilio(false);
							venda.setData_entrega(null);
						}
						int opcao;
						do {
							System.out.println("1-Processando.");
							System.out.println("2-Aguardando retirada/Envio.");
							System.out.println("3-Concluido.");
							opcao = scanner.nextInt();
							//scanner.reset();
							if(opcao>0 & opcao<4) {
								venda.setStatus(opcao);	
							}else {
								opcao=0;
							}
												
						}while(opcao==0);
						
						if (venda.update_banco()!="erro") {
							System.out.println("Venda:"+venda.getId());
							System.out.println("Venda Atualizado com Sucesso:");
						}else {
							System.out.println("Venda Não Atualizado com Sucesso:");
						}
					}else {
						System.out.println("Venda Não Atualizado com Sucesso:");
					}
					
					break;
				case 11:
					System.out.println("O Id da Venda que vc deseja DELETAR:");
					id =scanner.nextInt();
					if( venda.procurar_venda(id)!=null) {
						if (venda.delete_banco()!="erro") {
							System.out.println("Produto Deletado com Sucesso:");
						}else {
							System.out.println("Produto Não Deletado com Sucesso:");
						}
					}else {
						System.out.println("Produto Não Deletado com Sucesso:");
					}
					break;
				case 12:
					List<Vendas> vendas = new ArrayList<>();
					vendas=venda.listar_vendas();
					for(int i=0;i<vendas.size();i++) {
						System.out.println(vendas.get(i).toString());						
					}
					
					break;
				case 13:
					System.out.println("programa finalizado.");
					break;
				default:
					System.out.println("Opção inválida.");
	                break;
			}
		}while (op != 13);
		

      
        
        
        scanner.close();
		
		
	}
}
