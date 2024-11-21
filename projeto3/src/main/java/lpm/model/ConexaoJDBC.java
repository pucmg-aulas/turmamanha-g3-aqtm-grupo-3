package lpm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {
    // URL de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/xulambsdb?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "pmbbetim";

    // Conexão única para o padrão Singleton
    private static Connection connection;

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() {
        if (connection == null) {
            synchronized (ConexaoJDBC.class) { // Sincronização para garantir segurança em multi-thread
                if (connection == null) {
                    try {
                        // Carrega o driver do MySQL
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        System.out.println("Conexão estabelecida com sucesso.");
                    } catch (SQLException e) {
                        System.err.println("Erro ao estabelecer a conexão com o banco de dados: " + e.getMessage());
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        System.err.println("Driver JDBC não encontrado.");
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

    // Método para fechar a conexão explicitamente
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
                connection = null; // Reseta a conexão para permitir nova conexão no futuro
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

