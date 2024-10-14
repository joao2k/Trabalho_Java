package Venda_Croche;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Vendas {

	private LocalDateTime data_compra;
	private int id;
	private int id_cliente;
	private int id_produto;
	private float valor;
	private boolean entrega_domicilio;
	private int status;
	private LocalDate data_entrega;
	private String oberservacao;
	
	
	private Cenexao_banco cenexao = new Cenexao_banco("jdbc:mysql://localhost:3306/project_java","root","root" );
	
	//Vindo do sql
	public Vendas(LocalDateTime data_compra, int id, int id_cliente, int id_produto, float valor, boolean entrega_domicilio,
		int status, LocalDate data_entrega, String oberservacao) {
		this.data_compra = data_compra;
		this.id = id;
		this.id_cliente = id_cliente;
		this.id_produto = id_produto;
		this.valor = valor;
		this.entrega_domicilio = entrega_domicilio;
		this.status = status;
		this.data_entrega = data_entrega;
		this.oberservacao = oberservacao;
	}
	
	
	public Vendas(int id_cliente, int id_produto, float valor, boolean entrega_domicilio, int status,
			LocalDate data_entrega, String oberservacao) {
		this.id_cliente = id_cliente;
		this.id_produto = id_produto;
		this.valor = valor;
		this.entrega_domicilio = entrega_domicilio;
		this.status = status;
		this.data_entrega = data_entrega;
		this.oberservacao = oberservacao;
	}
	
	
	
	
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public LocalDate getData_entrega() {
		return data_entrega;
	}
	public void setData_entrega(LocalDate data_entrega) {
		this.data_entrega = data_entrega;
	}
	public String getOberservacao() {
		return oberservacao;
	}
	public void setOberservacao(String oberservacao) {
		this.oberservacao = oberservacao;
	}
	public int getId() {
		return id;
	}
	public LocalDateTime getData_compra() {
		return data_compra;
	}
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public boolean getEntrega_domicilio() {
		return entrega_domicilio;
	}
	public void setEntrega_domicilio(boolean entrega_domicilio) {
		this.entrega_domicilio = entrega_domicilio;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	// Banco
	
	public String insert_banco() {
		
		int resp=cenexao.insert_venda(this);
		if (resp != 0) {
			this.id=resp;
			return "sucesso";
		}else {
			return "erro";
		}
		
		
	}
	
	public Vendas procurar_venda(int id) { 
	    Vendas resp = cenexao.select_venda(id, this);
	    if (resp != null) {
	    	this.data_compra = resp.data_compra;
			this.id = resp.id;
			this.id_cliente = resp.id_cliente;
			this.id_produto = resp.id_produto;
			this.valor = resp.valor;
			this.entrega_domicilio = resp.entrega_domicilio;
			this.status = resp.status;
			this.data_entrega = resp.data_entrega;
			this.oberservacao = resp.oberservacao;
	        return this;
	    } else {
	        return null;
	    }
	}
	
	
public String update_banco() {
		
		
		String resp=cenexao.update_venda(this);
		if (resp != "erro") {
			return "sucesso";
		}else {
			return "erro";
		}
		
		
	}
public String delete_banco() {
	
	
	String resp=cenexao.delete_venda( this);
	if (resp != "erro") {
		return "sucesso";
	}else {
		return "erro";
	}
	
	
}



public List<Vendas> listar_vendas() {
	List<Vendas> vendas = new ArrayList<>();
	vendas=cenexao.select_todos_vendas();
	return vendas;
}


public String toString() {
	switch(status) {
	case 1:
		if(entrega_domicilio) {
			return "ID:"+id+"\t Data da Compra:"+data_compra+"\t Id do cliente:"+id_cliente +"\tId do Produto:"+id_produto+"\tValor:"+valor +"Status:Processando\tEntrega Para o dia :"+data_entrega+"\tObservação:"+oberservacao;
		}else {
			return "ID:"+id+"\t Data da Compra:"+data_compra+"\t Id do cliente:"+id_cliente +"\tId do Produto:"+id_produto+"\tValor:"+valor +"Status:Processando\tObservação:"+oberservacao;
		}
		
		
	case 2:
		if(entrega_domicilio) {
			return "ID:"+id+"\t Data da Compra:"+data_compra+"\t Id do cliente:"+id_cliente +"\tId do Produto:"+id_produto+"\tValor:"+valor +"Status:Aguardando Envio/Busca\tEntrega Para o dia :"+data_entrega+"\tObservação:"+oberservacao;
		}else {
			return "ID:"+id+"\t Data da Compra:"+data_compra+"\t Id do cliente:"+id_cliente +"\tId do Produto:"+id_produto+"\tValor:"+valor +"Status:Aguardando Envio/Busca\tObservação:"+oberservacao;
		}
		
	case 3:
		if(entrega_domicilio) {
			return "ID:"+id+"\t Data da Compra:"+data_compra+"\t Id do cliente:"+id_cliente +"\tId do Produto:"+id_produto+"\tValor:"+valor +"Status:Concluido\tEntrega Para o dia :"+data_entrega+"\tObservação:"+oberservacao;
		}else {
			return "ID:"+id+"\t Data da Compra:"+data_compra+"\t Id do cliente:"+id_cliente +"\tId do Produto:"+id_produto+"\tValor:"+valor +"Status:Concluido\tObservação:"+oberservacao;
		}
		
	
	}
	return "";
	
    
}
	
}
