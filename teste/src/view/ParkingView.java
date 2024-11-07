package view;

import javax.swing.*;

public class ParkingView extends JFrame{
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    public ParkingView() {
        super("Parking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label1 = new JLabel("Parking");
        label1.setBounds(10, 10, 100, 20);
        panel.add(label1);

        JLabel label2 = new JLabel("Car");
        label2.setBounds(10, 40, 100, 20);
        panel.add(label2);

        JLabel label3 = new JLabel("Motorcycle");
        label3.setBounds(10, 70, 100, 20);
        panel.add(label3);

        JLabel label4 = new JLabel("Bicycle");
        label4.setBounds(10, 100, 100, 20);
        panel.add(label4);

        comboBox1 = new JComboBox();
        comboBox1.setBounds(120, 10, 100, 20);
        panel.add(comboBox1);

        comboBox2 = new JComboBox();
        comboBox2.setBounds(120, 40, 100, 20);
        panel.add(comboBox2);

        comboBox3 = new JComboBox();
        comboBox3.setBounds(120, 70, 100, 20);
        panel.add(comboBox3);

        JButton button1 = new JButton("Add");
        button1.setBounds(10, 130, 100, 20);
        panel.add(button1);

        JButton button2 = new JButton("Remove");
        button2.setBounds(120, 130, 100, 20);
        panel.add(button2);

        JButton button3 = new JButton("Exit");
        button3.setBounds(230, 130, 100, 20);
        panel.add(button3);

        setContentPane(panel);
    }


}
