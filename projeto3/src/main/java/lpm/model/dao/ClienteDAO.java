package lpm.model.dao;

import lpm.model.Cliente;
import lpm.model.ConexaoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    public void cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTES (ID_CLIENTE, NOME) VALUES (?, ?)";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, cliente.getId());
            ps.setString(2, cliente.getNome());

            ps.execute();

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
