package lpm.model.dao;

import lpm.model.ConexaoJDBC;
import lpm.model.Vaga;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VagaDAO {

    public void cadastrarVaga(Vaga vaga, String nomeEstacionamento) {
        String sql = "INSERT INTO VAGAS (ID_VAGA, NOME_ESTACIONAMENTO, ESTADO) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, vaga.getId());
            ps.setString(2, nomeEstacionamento);
            ps.setBoolean(3, vaga.disponivel());

            ps.execute();

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Vaga> lerVagas(String nomeEstacionamento) {
        ArrayList<Vaga> vagas = new ArrayList<Vaga>();
        String id_vaga;
        boolean disponivel;

        String sql = "SELECT ID_VAGA, ESTADO FROM VAGAS WHERE NOME_ESTACIONAMENTO = ?";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, nomeEstacionamento);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()) {
                id_vaga = rs.getString("id_vaga");
                disponivel = rs.getBoolean("estado");

                vagas.add(new Vaga(id_vaga, disponivel));
            }

            return vagas;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizarEstadoVaga(String idVaga, String nomeEstacionamento) {
        String sql = "UPDATE VAGAS SET ESTADO = NOT ESTADO WHERE ID_VAGA = ? AND NOME_ESTACIONAMENTO = ?";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, idVaga);
            ps.setString(2, nomeEstacionamento);

            ps.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
