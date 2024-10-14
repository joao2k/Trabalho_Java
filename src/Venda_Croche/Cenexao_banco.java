package Venda_Croche;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class Cenexao_banco {
private String url = "jdbc:mysql://localhost:3306/project_java"; 
private String user = "root";  
private  String password = "root";



public Cenexao_banco(String url, String user, String password) {
	this.url = url;
	this.user = user;
	this.password = password;
}  


public int insert_cliente(Cliente cliente) {
	 try {
		 Connection connection = DriverManager.getConnection(url, user, password);
		 String sql="INSERT INTO clientes (nome, cpf, data_nascimento, endereco) VALUES (?, ?, ?, ?)";
		 PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		 preparedStatement.setString(1, cliente.getNome());
		 preparedStatement.setString(2, cliente.getCpf());
		 preparedStatement.setDate(3, Date.valueOf(cliente.getData_nascimento()));
		 preparedStatement.setString(4, cliente.getEndereco());
		 preparedStatement.executeUpdate();
		 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
         if (generatedKeys.next()) {
             int idGerado = generatedKeys.getInt(1);
             if (preparedStatement != null) preparedStatement.close();
    		 if (connection != null) connection.close();
             return idGerado;
         }
		 if (preparedStatement != null) preparedStatement.close();
		 if (connection != null) connection.close();
		 return 0;
	 
	 } catch (SQLException e) {
		return 0;
	}
}
public String update_cliente(Cliente cliente) {
	 try {
		 Connection connection = DriverManager.getConnection(url, user, password);
		 String sql="UPDATE clientes SET nome = ?, cpf = ?, data_nascimento = ?, endereco = ? WHERE id = ?";
		 PreparedStatement preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.setString(1, cliente.getNome());
		 preparedStatement.setString(2, cliente.getCpf());
		 preparedStatement.setDate(3, Date.valueOf(cliente.getData_nascimento()));
		 preparedStatement.setString(4, cliente.getEndereco());
		 preparedStatement.setInt(5, cliente.getID());
		 int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected!=0) {
        	if (preparedStatement != null) preparedStatement.close();
   		 	if (connection != null) connection.close();
            return "Sucesso";
        }
		 if (preparedStatement != null) preparedStatement.close();
		 if (connection != null) connection.close();
		 return "erro";
	 
	 } catch (SQLException e) {
		return "erro";
	}
}

public String delete_cliente(Cliente cliente) {
	 try {
		 Connection connection = DriverManager.getConnection(url, user, password);
		 String sql="DELETE FROM clientes WHERE id = ?";
		 PreparedStatement preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.setInt(1, cliente.getID());
		 int rowsAffected = preparedStatement.executeUpdate();
       if (rowsAffected!=0) {
       	if (preparedStatement != null) preparedStatement.close();
  		 	if (connection != null) connection.close();
           return "Sucesso";
       }
		 if (preparedStatement != null) preparedStatement.close();
		 if (connection != null) connection.close();
		 return "erro";
	 
	 } catch (SQLException e) {
		return "erro";
	}
	 
	 
}


public Cliente select_cliente(int id_procura, Cliente cliente) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    try {
        connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM clientes WHERE id = "+id_procura;
        preparedStatement = connection.prepareStatement(sql);

        resultSet = preparedStatement.executeQuery();
        
        // Verifica se há algum resultado
        if (resultSet.next()) { 
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            String endereco = resultSet.getString("endereco");
            LocalDate dataNascimento = resultSet.getDate("data_nascimento").toLocalDate();

            
            Cliente resp = new Cliente(id, nome, cpf, dataNascimento, endereco); 
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
            return resp;
        } else {
        	
            return null; 
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}



public List<Cliente> select_todos_clientes() {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT * FROM clientes";

    try (Connection connection = DriverManager.getConnection(url, user, password);
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            String endereco = resultSet.getString("endereco");
            LocalDate dataNascimento = resultSet.getDate("data_nascimento").toLocalDate();
            Cliente cliente = new Cliente(id,cpf , nome, dataNascimento, endereco);
            clientes.add(cliente);
        }
        
        if (preparedStatement != null) preparedStatement.close();
    	if (connection != null) connection.close();
        return clientes;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
     
}

//Produto
public int insert_produto(Produto produto) {
	 try {
		 Connection connection = DriverManager.getConnection(url, user, password);
		 String sql="INSERT INTO produto (tipo, cor) VALUES (?, ?)";// mudar a query para os atrubutos do produto 
		 PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		 preparedStatement.setString(1, produto.getTipo());//mexer nisso
		 preparedStatement.setString(2, produto.getCor());
		 preparedStatement.executeUpdate();
		 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int idGerado = generatedKeys.getInt(1);
            if (preparedStatement != null) preparedStatement.close();
   		 if (connection != null) connection.close();
            return idGerado;
        }
		 if (preparedStatement != null) preparedStatement.close();
		 if (connection != null) connection.close();
		 return 0;
	 
	 } catch (SQLException e) {
		return 0;
	}
}


public String update_produto(Produto produto) {
	 try {
		 Connection connection = DriverManager.getConnection(url, user, password);
		 String sql="UPDATE produto SET Tipo = ?, Cor = ? WHERE id = ?";
		 PreparedStatement preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.setString(1, produto.getTipo());
		 preparedStatement.setString(2, produto.getCor());
		 preparedStatement.setInt(3, produto.getId());
		 int rowsAffected = preparedStatement.executeUpdate();
       if (rowsAffected!=0) {
       	if (preparedStatement != null) preparedStatement.close();
  		 	if (connection != null) connection.close();
           return "Sucesso";
       }
		 if (preparedStatement != null) preparedStatement.close();
		 if (connection != null) connection.close();
		 return "erro";
	 
	 } catch (SQLException e) {
		return "erro";
	}
}

public Produto select_produto(int id_procura, Produto produto) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    try {
        connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM produto WHERE id = "+id_procura;
        preparedStatement = connection.prepareStatement(sql);

        resultSet = preparedStatement.executeQuery();
        
        // Verifica se há algum resultado
        if (resultSet.next()) { 
            int id = resultSet.getInt("id");
            String tipo = resultSet.getString("tipo");
            String cor = resultSet.getString("cor");

            
            Produto resp = new Produto(cor,id,tipo); 
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
            return resp;
        } else {
        	
            return null; 
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

public String delete_produto(Produto produto) {
	 try {
		 Connection connection = DriverManager.getConnection(url, user, password);
		 String sql="DELETE FROM produto WHERE id = ?";
		 PreparedStatement preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.setInt(1, produto.getId());
		 int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected!=0) {
      	if (preparedStatement != null) preparedStatement.close();
 		 	if (connection != null) connection.close();
          return "Sucesso";
      }
		 if (preparedStatement != null) preparedStatement.close();
		 if (connection != null) connection.close();
		 return "erro";
	 
	 } catch (SQLException e) {
		return "erro";
	}
	 
	 
}



public List<Produto> select_todos_produtos() {
    List<Produto> produtos = new ArrayList<>();
    String sql = "SELECT * FROM produto";

    try (Connection connection = DriverManager.getConnection(url, user, password);
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String tipo = resultSet.getString("tipo");
            String cor = resultSet.getString("cor");
            Produto produto = new Produto(cor,id ,tipo);
            produtos.add(produto);
        }
        
        if (preparedStatement != null) preparedStatement.close();
    	if (connection != null) connection.close();
        return produtos;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
     
}

//Venda

public int insert_venda(Vendas venda) {
	try {
	    Connection connection = DriverManager.getConnection(url, user, password);
	    if (venda.getData_entrega() != null) {
	    	String sql = "INSERT INTO vendas (data_compra, id_cliente, id_produto, valor, entrega_domicilio, status, data_entrega, observacao) VALUES (NOW(), ?, ?, ?, ?, ?, ?, ?)";
	 	    PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	 	    preparedStatement.setInt(1, venda.getId_cliente());
	 	    preparedStatement.setInt(2, venda.getId_produto());
	 	    preparedStatement.setFloat(3, venda.getValor());
	 	    preparedStatement.setBoolean(4, venda.getEntrega_domicilio());
	 	    preparedStatement.setInt(5, venda.getStatus());
	 	    preparedStatement.setDate(6, Date.valueOf(venda.getData_entrega()));
	 	    preparedStatement.setString(7, venda.getOberservacao());
	 	    preparedStatement.executeUpdate();
		    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		    if (generatedKeys.next()) {
		        int idGerado = generatedKeys.getInt(1); 
		        System.out.println("ID gerado: " + idGerado);
		        if (preparedStatement != null) preparedStatement.close();
		        if (connection != null) connection.close();

		        return idGerado;
		    }
		    if (preparedStatement != null) preparedStatement.close();
		    if (connection != null) connection.close();
		    return 0;  
	    } else {
	    	String sql = "INSERT INTO vendas (data_compra, id_cliente, id_produto, valor, entrega_domicilio, status,  observacao) VALUES (NOW(), ?, ?, ?, ?, ?, ?)";
	 	    PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	 	    preparedStatement.setInt(1, venda.getId_cliente());
	 	    preparedStatement.setInt(2, venda.getId_produto());
	 	    preparedStatement.setFloat(3, venda.getValor());
	 	    preparedStatement.setBoolean(4, venda.getEntrega_domicilio());
	 	    preparedStatement.setInt(5, venda.getStatus());
	 	    preparedStatement.setString(6, venda.getOberservacao());
	 	    preparedStatement.executeUpdate();
		    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		    if (generatedKeys.next()) {
		        int idGerado = generatedKeys.getInt(1); 
		        System.out.println("ID gerado: " + idGerado);
		        if (preparedStatement != null) preparedStatement.close();
		        if (connection != null) connection.close();

		        return idGerado;
		    }
		    if (preparedStatement != null) preparedStatement.close();
		    if (connection != null) connection.close();
		    return 0;  
	    }
	   
	    
	   
	} catch (SQLException e) {
	    e.printStackTrace();
	    return 0; 
	}

}



public Vendas select_venda(int id_procura, Vendas venda) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    try {
        connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM vendas WHERE id = "+id_procura;
        preparedStatement = connection.prepareStatement(sql);

        resultSet = preparedStatement.executeQuery();
        
        // Verifica se há algum resultado
        if (resultSet.next()) { 
        	int id = resultSet.getInt("id");
        	int id_cliente = resultSet.getInt("id_cliente");
        	int id_produto = resultSet.getInt("id_produto");
        	float valor = resultSet.getFloat("valor");
        	boolean entrega_domicilio = resultSet.getBoolean("entrega_domicilio");
        	int status = resultSet.getInt("status");
        	Timestamp timestampCompra = resultSet.getTimestamp("data_compra");
        	LocalDateTime data_compra = timestampCompra.toLocalDateTime(); 
        	String observacao = resultSet.getString("observacao");  
        	if (resultSet.getDate("data_entrega") != null) {
        		LocalDate data_entrega =  resultSet.getDate("data_entrega").toLocalDate();
        		Vendas resp = new Vendas(data_compra,id,id_cliente,id_produto,valor,entrega_domicilio,status,data_entrega,observacao);
        		if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
                return resp;
        	}else {
        		System.out.println("programa finalizado.");
        		Vendas resp = new Vendas(data_compra,id, id_cliente,id_produto,valor,entrega_domicilio,status,null,observacao);
        		if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
                return resp;
        	}
        } else {
        	
            return null; 
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}



public String update_venda(Vendas venda) {
    String sql = "UPDATE vendas SET id_cliente = ?, id_produto = ?, valor = ?, entrega_domicilio = ?, status = ?, data_entrega = ?, observacao = ? WHERE id = ?";

    try (Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, venda.getId_cliente());
        preparedStatement.setInt(2, venda.getId_produto());
        preparedStatement.setFloat(3, venda.getValor());
        preparedStatement.setBoolean(4, venda.getEntrega_domicilio());
        preparedStatement.setInt(5, venda.getStatus());
        if (venda.getData_entrega() != null) {
            preparedStatement.setDate(6, Date.valueOf(venda.getData_entrega())); 
        } else {
            preparedStatement.setNull(6, java.sql.Types.DATE);
        }
        preparedStatement.setString(7, venda.getOberservacao());
        preparedStatement.setInt(8, venda.getId());
        
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected!=0) {
        	if (preparedStatement != null) preparedStatement.close();
   		 	if (connection != null) connection.close();
            return "Sucesso";
        }
		 if (preparedStatement != null) preparedStatement.close();
		 if (connection != null) connection.close();
		 return "erro";
	 
	 } catch (SQLException e) {
		return "erro";
	}
}


public String delete_venda(Vendas vendas) {
	 try {
		 Connection connection = DriverManager.getConnection(url, user, password);
		 String sql="DELETE FROM vendas WHERE id = ?";
		 PreparedStatement preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.setInt(1, vendas.getId());
		 int rowsAffected = preparedStatement.executeUpdate();
     if (rowsAffected!=0) {
     	if (preparedStatement != null) preparedStatement.close();
		 	if (connection != null) connection.close();
         return "Sucesso";
     }
		 if (preparedStatement != null) preparedStatement.close();
		 if (connection != null) connection.close();
		 return "erro";
	 
	 } catch (SQLException e) {
		return "erro";
	}
	 
	 
}


public List<Vendas> select_todos_vendas() {
    List<Vendas> vendas = new ArrayList<>();
    String sql = "SELECT * FROM vendas";

    try (Connection connection = DriverManager.getConnection(url, user, password);
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()) {

    	while (resultSet.next()) {
    		LocalDateTime dataCompra = resultSet.getTimestamp("data_compra").toLocalDateTime();
    	    int id = resultSet.getInt("id");
    	    int idCliente = resultSet.getInt("id_cliente");
    	    int idProduto = resultSet.getInt("id_produto");
    	    float valor = resultSet.getFloat("valor");
    	    boolean entregaDomicilio = resultSet.getBoolean("entrega_domicilio");
    	    int status = resultSet.getInt("status");
    	    LocalDate dataEntrega;
    	    if(resultSet.getDate("data_entrega") != null ) {
    	    	 dataEntrega=resultSet.getDate("data_entrega").toLocalDate();
    	    }else
    	    	dataEntrega = null;
    	   // Verifica se data_entrega é nulo
    	    String observacao = resultSet.getString("observacao");
    	    
    	    Vendas venda = new Vendas(dataCompra, id, idCliente, idProduto, valor, entregaDomicilio, status, dataEntrega, observacao);
    	    vendas.add(venda);
    	}
        
        if (preparedStatement != null) preparedStatement.close();
    	if (connection != null) connection.close();
        return vendas;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
     
}


}
