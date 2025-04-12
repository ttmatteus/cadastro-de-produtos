package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConexaoSingleton {
    private static Connection conexao;
    private static boolean isConectado = false;

    public static Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                Properties props = new Properties();
                InputStream input = ConexaoSingleton.class.getClassLoader()
                        .getResourceAsStream("application.properties");

                if (input == null) {
                    throw new RuntimeException("Arquivo application.properties não encontrado.");
                }

                props.load(input);

                String url = props.getProperty("db.url");

                conexao = DriverManager.getConnection(url);

                if (!isConectado) {
                    System.out.println("Conexão SQLite estabelecida com sucesso!");
                    isConectado = true;
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }

        return conexao;
    }
}
