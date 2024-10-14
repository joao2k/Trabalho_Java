package Venda_Croche;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class testes {

    public static void main(String[] args) {
        // URL de conexão ao banco de dados
        String url = "jdbc:mysql://localhost:3306/project_java"; // Exemplo com MySQL
        String user = "root";  // Usuário do banco de dados
        String password = "root";  // Senha do banco de dados

        Connection connection = null;

        try {
            // Conectar ao banco de dados
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida!");

            // Criar um objeto Statement para executar consultas
            Statement statement = connection.createStatement();

            // Executar uma consulta simples
            String query = "SELECT * FROM teste";
            ResultSet resultSet = statement.executeQuery(query);

            // Processar os resultados da consulta
            while (resultSet.next()) {
                System.out.println("idade: " + resultSet.getInt("idade"));
                System.out.println("Nome: " + resultSet.getString("Nome"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        } finally {
            // Fechar a conexão ao banco de dados
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

