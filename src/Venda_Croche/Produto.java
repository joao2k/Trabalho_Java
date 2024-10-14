package Venda_Croche;
import java.util.ArrayList;
import java.util.List;

public class Produto {
private String cor;
private int id ;
private String tipo;

private Cenexao_banco cenexao = new Cenexao_banco("jdbc:mysql://localhost:3306/project_java","root","root" );
//gerar objeto com os dados do SQL
public Produto(String cor, int id, String tipo) {
	this.cor = cor;
	this.id = id;
	this.tipo = tipo;
}
//gerar para enviar ao banco
public Produto(String cor, String tipo) {
	this.cor = cor;
	this.tipo = tipo;
}


public String getCor() {
	return cor;
}
public void setCor(String cor) {
	this.cor = cor;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}

  
public int getId() {
	return id;
}
public String insert_banco() {
	
	int resp=cenexao.insert_produto(this);
	if (resp != 0) {
		this.id=resp;
		return "sucesso";
	}else {
		return "erro";
	}
	
	
}

public Produto procurar_produto(int id) { 
    Produto resp = cenexao.select_produto(id, this);
    if (resp != null) {
        this.id = resp.id;
        this.cor = resp.cor;
        this.tipo = resp.tipo;
        return this;
    } else {
        return null;
    }
}


public String update_banco() {
	
	
	String resp=cenexao.update_produto(this);
	if (resp != "erro") {
		return "sucesso";
	}else {
		return "erro";
	}
	
	
}


public String delete_banco() {
	
	
	String resp=cenexao.delete_produto( this);
	if (resp != "erro") {
		return "sucesso";
	}else {
		return "erro";
	}
	
	
}


public List<Produto> listar_produtos() {
	List<Produto> produtos = new ArrayList<>();
	produtos=cenexao.select_todos_produtos();
	return produtos;
}


public String toString() {
    return "ID:"+id+"\t Cor:"+cor+"\t Tipo:"+tipo;
}

}



