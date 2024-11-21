package lpm.model.dao;

import lpm.model.ConexaoJDBC;
import lpm.model.UsoDeVaga;
import lpm.model.Vaga;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UsoDeVagaDAO {

    public void cadastrarUsoDeVaga(UsoDeVaga usoDeVaga, String nomeEstacionamento, String placaVeiculo) {
        String sql = "INSERT INTO USOS_DE_VAGA (ID_VAGA, NOME_ESTACIONAMENTO, PLACA_VEICULO, ENTRADA, SAIDA) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, usoDeVaga.getVaga().getId());
            ps.setString(2, nomeEstacionamento);
            ps.setString(3, placaVeiculo);
            ps.setString(4, usoDeVaga.getEntrada().toString());

            if(usoDeVaga.getSaida() == null) { // testando se o uso possui saida registrada
                ps.setString(5, "null");
            } else {
                ps.setString(5, usoDeVaga.getSaida().toString());
            }

            ps.execute();

            ps.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UsoDeVaga> lerUsosDeVaga(String nomeEstacionamento, String placa) {
        ArrayList<UsoDeVaga> usosDeVaga = new ArrayList<UsoDeVaga>();
        String id_vaga, entrada, saida;
        Vaga vagaAux;
        LocalDateTime saidaTratada;
        boolean estadoVaga;

        String sql = "SELECT ID_VAGA, ENTRADA, SAIDA FROM USOS_DE_VAGA WHERE NOME_ESTACIONAMENTO = ? AND PLACA_VEICULO = ?";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, nomeEstacionamento);
            ps.setString(2, placa);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()) {
                id_vaga = rs.getString("id_vaga");
                entrada = rs.getString("entrada");
                saida = rs.getString("saida");

                if(saida.equalsIgnoreCase("null")) {
                    estadoVaga = false;
                    saidaTratada = null;
                } else {
                    estadoVaga = true;
                    saidaTratada = LocalDateTime.parse(saida);
                }
                vagaAux = new Vaga(id_vaga, estadoVaga);
                usosDeVaga.add(new UsoDeVaga(vagaAux, LocalDateTime.parse(entrada), saidaTratada));
            }

            return usosDeVaga;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] consultarInfoUsoAtual(String idVaga, String nomeEstacionamento) {
        String[] dados = new String[2];
        String placa_veiculo, entrada;

        String sql = "SELECT PLACA_VEICULO, ENTRADA FROM USOS_DE_VAGA WHERE ID_VAGA = ? AND NOME_ESTACIONAMENTO = ? AND SAIDA = 'null'";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, idVaga);
            ps.setString(2, nomeEstacionamento);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()) {
                placa_veiculo = rs.getString("placa_veiculo");
                entrada = rs.getString("entrada");

                dados[0] = placa_veiculo;
                dados[1] = entrada;
            }

            return dados;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void registrarSaida(String placa, String entrada, String saida) {
        String sql = "UPDATE USOS_DE_VAGA SET SAIDA = ? WHERE PLACA_VEICULO = ? AND ENTRADA = ?";

        try{
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, saida);
            ps.setString(2, placa);
            ps.setString(3, entrada);

            ps.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean estaEstacionado(String placa) {
        String sql = "SELECT MAX(PLACA_VEICULO = ? AND SAIDA = 'null') AS 'ESTA_ESTACIONADO' FROM USOS_DE_VAGA";

        try {
            PreparedStatement ps = ConexaoJDBC.getConnection().prepareStatement(sql);

            ps.setString(1, placa);

            ps.execute();

            ResultSet rs = ps.getResultSet();
            rs.next();

            if(rs.getInt("esta_estacionado") == 1) {
                return true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
