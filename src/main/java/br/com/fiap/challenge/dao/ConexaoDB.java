package br.com.fiap.challenge.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    // Definindo as informações de conexão com o banco de dados Oracle
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "RM550689";
    private static final String PASSWORD = "050704";

    // Registrar o driver JDBC do Oracle
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do Oracle não encontrado: " + e.getMessage());
        }
    }

    // Método para obter uma conexão com o banco de dados Oracle
    public static Connection getConnection() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco de dados Oracle estabelecida.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados Oracle: " + e.getMessage());
        }
        return conexao;
    }

    // Método para fechar a conexão
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o banco de dados Oracle fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão com o banco de dados Oracle: " + e.getMessage());
            }
        }
    }
}
