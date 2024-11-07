package view;

import controller.ClienteController;
import controller.VagaController;
import controller.HistoricoController;
import controller.EstacionamentoController;
import controller.RankingController;
import model.Cliente;
import model.Vaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ClienteViewGUI extends JFrame {
    private final ClienteController clienteController;
    private final VagaController vagaController;
    private final HistoricoController historicoController;
    private final EstacionamentoController estacionamentoController;
    private final RankingController rankingController;

    public ClienteViewGUI() {
        clienteController = new ClienteController();
        vagaController = new VagaController();
        historicoController = new HistoricoController();
        estacionamentoController = new EstacionamentoController();
        rankingController = new RankingController();

        setTitle("Sistema de Estacionamento");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
        JButton btnVisualizarVagas = new JButton("Visualizar Vagas Disponíveis");
        JButton btnOcuparVaga = new JButton("Ocupar Vaga");
        JButton btnGerarFatura = new JButton("Gerar Fatura");
        JButton btnVisualizarHistorico = new JButton("Visualizar Histórico");
        JButton btnVisualizarRanking = new JButton("Visualizar Ranking de Clientes");
        JButton btnCalcularArrecadacaoTipo = new JButton("Calcular Arrecadação por Tipo de Vaga");
        JButton btnCalcularTotalArrecadado = new JButton("Calcular Total Arrecadado");

        btnCadastrarCliente.addActionListener(e -> cadastrarCliente());
        btnVisualizarVagas.addActionListener(e -> visualizarVagasDisponiveis());
        btnOcuparVaga.addActionListener(e -> ocuparVaga());
        btnGerarFatura.addActionListener(e -> gerarFatura());
        btnVisualizarHistorico.addActionListener(e -> visualizarHistorico());
        btnVisualizarRanking.addActionListener(e -> visualizarRanking());
        btnCalcularArrecadacaoTipo.addActionListener(e -> calcularArrecadadoPorTipo());
        btnCalcularTotalArrecadado.addActionListener(e -> calcularTotalArrecadado());

        panel.add(btnCadastrarCliente);
        panel.add(btnVisualizarVagas);
        panel.add(btnOcuparVaga);
        panel.add(btnGerarFatura);
        panel.add(btnVisualizarHistorico);
        panel.add(btnVisualizarRanking);
        panel.add(btnCalcularArrecadacaoTipo);
        panel.add(btnCalcularTotalArrecadado);

        add(panel);
    }

    private void cadastrarCliente() {
        String id = JOptionPane.showInputDialog("Digite o ID do cliente:");
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");

        Cliente cliente = new Cliente(id, nome);
        while (true) {
            String placa = JOptionPane.showInputDialog("Digite a placa do veículo (ou 'sair' para finalizar):");
            if (placa.equalsIgnoreCase("sair")) break;
            cliente.adicionarVeiculo(placa);
        }
        clienteController.salvarCliente(cliente);
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
    }

    private void visualizarVagasDisponiveis() {
        List<Vaga> vagasDisponiveis = vagaController.listarVagasDisponiveis();
        StringBuilder vagas = new StringBuilder("Vagas Disponíveis:\n");
        if (vagasDisponiveis.isEmpty()) {
            vagas.append("Nenhuma vaga disponível.");
        } else {
            for (Vaga vaga : vagasDisponiveis) {
                vagas.append(vaga).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, vagas.toString());
    }

    private void ocuparVaga() {
        String idCliente = JOptionPane.showInputDialog("Digite o ID do Cliente:");
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo:");
        String idVaga = JOptionPane.showInputDialog("Digite o ID da Vaga:");
        boolean ocupadaComSucesso = vagaController.ocuparVaga(idCliente, placa, idVaga);

        if (ocupadaComSucesso) {
            JOptionPane.showMessageDialog(this, "Vaga ocupada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao ocupar a vaga.");
        }
    }

    private void gerarFatura() {
        String idCliente = JOptionPane.showInputDialog("Digite o ID do cliente:");
        String idVaga = JOptionPane.showInputDialog("Digite o ID da Vaga:");
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo:");
        int horaEntrada = Integer.parseInt(JOptionPane.showInputDialog("Digite a hora de entrada (ex: 14):"));
        int minutoEntrada = Integer.parseInt(JOptionPane.showInputDialog("Digite os minutos de entrada (ex: 30):"));
        int horaSaida = Integer.parseInt(JOptionPane.showInputDialog("Digite a hora de saída (ex: 16):"));
        int minutoSaida = Integer.parseInt(JOptionPane.showInputDialog("Digite os minutos de saída (ex: 45):"));
        String data = JOptionPane.showInputDialog("Digite a data (ex: 2024-11-07):");

        Vaga vaga = vagaController.encontrarVagaPorId(idVaga);
        if (vaga == null) {
            JOptionPane.showMessageDialog(this, "Vaga não encontrada!");
            return;
        }

        long horas = horaSaida - horaEntrada;
        long minutos = minutoSaida - minutoEntrada;
        if (minutos < 0) {
            minutos += 60;
            horas -= 1;
        }

        double valorPago = vagaController.calcularPreco(horas, minutos, vaga);
        vagaController.salvarFatura(idCliente, idVaga, placa, valorPago, data);
        JOptionPane.showMessageDialog(this, "Fatura gerada com sucesso! Valor a pagar: R$ " + valorPago);
    }

    private void calcularArrecadadoPorTipo() {
        String tipoVaga = JOptionPane.showInputDialog("Digite o tipo de vaga (comum, idoso, PCD, VIP):").toLowerCase();
        String resultado = estacionamentoController.calcularArrecadadoPorTipo(tipoVaga);
        JOptionPane.showMessageDialog(this, resultado);
    }
    
    private void calcularTotalArrecadado() {
        String resultado = estacionamentoController.calcularTotalArrecadado();
        JOptionPane.showMessageDialog(this, resultado);
    }
    
    private void visualizarRanking() {
        List<String> ranking = rankingController.gerarRanking();
        StringBuilder rankingMessage = new StringBuilder("Ranking de Clientes por Gastos:\n");
        for (String clienteRanking : ranking) {
            rankingMessage.append(clienteRanking).append("\n");
        }
        JOptionPane.showMessageDialog(this, rankingMessage.toString());
    }
    
    private void visualizarHistorico() {
        String idCliente = JOptionPane.showInputDialog("Digite o ID do Cliente para ver o histórico:");
        String historico = historicoController.carregarHistoricoCliente(idCliente);
        JOptionPane.showMessageDialog(this, historico);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteViewGUI frame = new ClienteViewGUI();
            frame.setVisible(true);
        });
    }
}
