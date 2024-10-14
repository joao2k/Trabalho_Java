package Venda_Croche;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Cliente {
	private int id;
	private String cpf;
	private String nome;
	private LocalDate data_nascimento;
	private String endereco;
	
	private Cenexao_banco cenexao = new Cenexao_banco("jdbc:mysql://localhost:3306/project_java","root","root" );
	
	//gerar objeto com os dados do SQL
	public Cliente(int id, String cpf, String nome, LocalDate data_nascimento,String endereco) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.data_nascimento = data_nascimento;
		this.endereco = endereco;
	}
	//gerar para enviar ao banco
	public Cliente(String cpf, String nome, LocalDate data_nascimento,String endereco) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.data_nascimento = data_nascimento;
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	//aceso ao banco
	public String insert_banco() {
		
		int resp=cenexao.insert_cliente(this);
		if (resp != 0) {
			this.id=resp;
			return "sucesso";
		}else {
			return "erro";
		}
		
		
	}
	
	public String update_banco() {
		
		
		String resp=cenexao.update_cliente(this);
		if (resp != "erro") {
			return "sucesso";
		}else {
			return "erro";
		}
		
		
	}
	
	public String delete_banco() {
		
		
		String resp=cenexao.delete_cliente( this);
		if (resp != "erro") {
			return "sucesso";
		}else {
			return "erro";
		}
		
		
	}
	
	public int getID() {
		return this.id;
	}
	
	public List<Cliente> listar_clientes() {
		List<Cliente> clientes = new ArrayList<>();
		clientes=cenexao.select_todos_clientes();
		return clientes;
	}
	public Cliente procurar_cliente(int id) { 
	    Cliente resp = cenexao.select_cliente(id, this);
	    if (resp != null) {
	        this.id = resp.id;
	        this.cpf = resp.cpf;
	        this.nome = resp.nome;
	        this.data_nascimento = resp.data_nascimento;
	        this.endereco = resp.endereco;
	        return this;
	    } else {
	        return null;
	    }
	}

	public String toString() {
        return "ID:"+id+"\t Nome:"+nome+"\t CPF:"+cpf+"\t Data de Nascimento:"+data_nascimento+"\t Endereço:"+endereco;
    }
	
	
}
