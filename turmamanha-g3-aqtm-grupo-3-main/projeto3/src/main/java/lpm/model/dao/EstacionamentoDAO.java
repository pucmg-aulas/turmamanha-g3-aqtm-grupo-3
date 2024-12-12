package lpm.model.dao;

import lpm.model.ConexaoJDBC;
import lpm.model.Estacionamento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstacionamentoDAO {

    public void cadastrarEstacionamento(Estacionamento estacionamento) {
        String sql = "INSERT INTO ESTACIONAMENTOS (NOME_ESTACIONAMENTO, QUANTIDADE_FILEIRAS, VAGAS_POR_FILEIRA) VALUES (?, ?, ?)";

        try{
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, estacionamento.getNome());
            ps.setInt(2, estacionamento.getQuantFileiras());
            ps.setInt(3, estacionamento.getVagasPorFileira());

            ps.execute();

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estacionamento lerEstacionamento(String nomeEstacionamento) {
        Estacionamento estacionamentoAtual;
        String sql = "SELECT * FROM ESTACIONAMENTOS WHERE NOME_ESTACIONAMENTO = ?";
        int quantidade_fileiras, vagas_por_fileira;

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, nomeEstacionamento);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            if(rs.next()) {
                quantidade_fileiras = rs.getInt("quantidade_fileiras");
                vagas_por_fileira = rs.getInt("vagas_por_fileira");
                estacionamentoAtual = new Estacionamento(nomeEstacionamento, quantidade_fileiras, vagas_por_fileira);

                estacionamentoAtual.setVagas(new VagaDAO().lerVagas(estacionamentoAtual.getNome()));
                estacionamentoAtual.setClientes(new ClienteDAO().lerClientes(nomeEstacionamento));

                return estacionamentoAtual;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<String> lerNomesEstacionamentos() {
        ArrayList<String> nomes = new ArrayList<String>();
        String sql = "SELECT NOME_ESTACIONAMENTO FROM ESTACIONAMENTOS";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()) {
                nomes.add(rs.getString("nome_estacionamento"));
            }

            return nomes;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
