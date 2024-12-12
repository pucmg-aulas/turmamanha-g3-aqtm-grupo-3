package lpm.model.dao;

import lpm.model.Cliente;
import lpm.model.ConexaoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    public void cadastrarCliente(Cliente cliente) {
        String verificarSql = "SELECT COUNT(*) FROM CLIENTES WHERE id_cliente = ?";
        String inserirSql = "INSERT INTO CLIENTES (id_cliente, nome) VALUES (?,?)";

        try {
            // Verifica se o cliente já existe
            PreparedStatement verificarPs = ConexaoJDBC.getConnection().prepareStatement(verificarSql);
            verificarPs.setString(1, cliente.getId());
            ResultSet rs = verificarPs.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            verificarPs.close();

            if (count > 0) {
                // Remove mensagens desnecessárias
                return; // Evita duplicação
            }

            // Insere o cliente
            PreparedStatement inserirPs = ConexaoJDBC.getConnection().prepareStatement(inserirSql);
            inserirPs.setString(1, cliente.getId());
            inserirPs.setString(2, cliente.getNome());
            inserirPs.execute();
            inserirPs.close();

            // Mensagem de sucesso pode ser mantida ou removida
            System.out.println("Cliente cadastrado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage(), e);
        }
    }

    public ArrayList<Cliente> lerClientes(String nomeEstacionamento) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        String id_cliente, nome;
        String sql = "SELECT * FROM CLIENTES";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()) {
                id_cliente = rs.getString("id_cliente");
                nome = rs.getString("nome");
                clientes.add(new Cliente(id_cliente, nome, new VeiculoDAO().lerVeiculos(id_cliente, nomeEstacionamento)));
            }

            return clientes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cliente buscarCliente(String cpfCliente) {
        String sql = "SELECT NOME FROM CLIENTES WHERE ID_CLIENTE = ?";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, cpfCliente);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();

            return new Cliente(cpfCliente, rs.getString("id_cliente"));
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
