package view;

import controller.AddCustomerController;
import controller.AddParkingController;
import controller.AddVehicleController;
import controller.CheckinController;
import dao.Parkings;
import model.Parking;
import model.ParkingSpot;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame{
    public JButton adiconarEstacionamentoButton;
    public JButton adicionarVeiculoButton;
    public JButton adicionarClienteButton;
    public JButton verFaturamentoTotalButton;
    public JButton verFaturamentoMensalButton;
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JPanel selectParkingPanel;
    private JPanel listButtonsPanel;
    private JButton verTicketMédioButton;
    private JButton top5ClientesButton;
    private JScrollPane tablePane;
    JTable table1;

    public Main() {
        super("Parking");
        initComponents();
    }

    private void initComponents() {

        comboBox1.addItem("Selecione um estacionamento");
        try {
            for(Parking parking : new Parkings().getInstance().parkings) {
                comboBox1.addItem(parking);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        comboBox1.addActionListener(e -> {
            if(comboBox1.getSelectedIndex() != 0) {
                Parking selectedItem = (Parking) comboBox1.getSelectedItem();
                setTable(selectedItem);
            }
        });


        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        adiconarEstacionamentoButton.addActionListener(e -> {
            try {
                new AddParkingController();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        adicionarClienteButton.addActionListener(e -> {
            if(comboBox1.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione um estacionamento");
                return;
            }

            Parking selectedItem = (Parking) comboBox1.getSelectedItem();
            new AddCustomerController(selectedItem);
        });

        adicionarVeiculoButton.addActionListener(e -> {
            if(comboBox1.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione um estacionamento");
                return;
            }

            Parking selectedItem = (Parking) comboBox1.getSelectedItem();
            new AddVehicleController(selectedItem);
        });
    }

    private void setTable(Parking parking) {

        ArrayList<ParkingSpot> parkingSpots = parking.parkingSpots;
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add(" ");

        for (ParkingSpot parkingSpot : parkingSpots) {
            final char letter = parkingSpot.getId().charAt(0);
            final int number = Integer.parseInt(parkingSpot.getId().substring(1));

            if(!columnNames.contains(String.valueOf(letter))) {
                columnNames.add(String.valueOf(letter));
            }
        }

        TableModel dataModel = new
                AbstractTableModel() {
                    public int getColumnCount() {
                        return columnNames.size();
                    }

                    public int getRowCount() {
                        return parking.getNumberOfRows();
                    }

                    public Object getValueAt(int row, int col) {
                        Object value = "";
                        if(col == 0) {
                            value = row + 1;
                        }
                        for (ParkingSpot parkingSpot : parkingSpots) {
                            final char letter = parkingSpot.getId().charAt(0);
                            final int number = Integer.parseInt(parkingSpot.getId().substring(1));

                            if (number == row + 1 && columnNames.get(col).equals(String.valueOf(letter))) {
                                value = parkingSpot.isAvailable() ? " " : "X";
                            }
                        }
                        return value;
                    }

                    public String getColumnName(int column) {
                        return columnNames.get(column);
                    }
                };

        JTable table = new JTable(dataModel) {
            public Component prepareRenderer(
                    TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if(column == 0) {
                    c.setBackground(Color.LIGHT_GRAY);
                    return c;
                }
                if (getValueAt(row, column).equals(" ")) {
                    c.setBackground(Color.GREEN);
                } else if (getValueAt(row, column).equals("X")) {
                    c.setBackground(Color.RED);
                }
                return c;
            }
            //print the value of the cell in the console when clicked
            public void changeSelection(int row, int column, boolean toggle, boolean extend) {
                super.changeSelection(row, column, toggle, extend);
                String spotId = getColumnName(column) + (row + 1);
                ParkingSpot parkingSpot = parking.getParkingSpot(spotId);

                if (parkingSpot.isAvailable()) {
                    new CheckinController(parking, parkingSpot);
                } else {
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Deseja fazer checkout?","Warning", JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION){
                    }
                }
            }
        };
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        table.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table.setFillsViewportHeight(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);
        tablePane.setViewportView(table);

    }

    public static void main(String[] args) {
        new Main();
    }
}
