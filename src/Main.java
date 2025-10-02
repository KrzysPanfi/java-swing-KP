

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {

    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Swing Tutorial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(8, 8));

        // === SECTION 1: Podstawowe przyciski i pola tekstowe ===
        JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // JButton z ActionListener
        JButton button = new JButton("Dodaj Panstwo");
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Kliknięto przycisk!"));
        basicPanel.add(button);
        frame.add(basicPanel, BorderLayout.SOUTH);
        JTextField textField = new JTextField(15);
        DefaultTableModel model = new DefaultTableModel();
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        model.addColumn("Flaga");
        model.addColumn("Kraj");
        model.addColumn("Wiki");

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String country = textField.getText();
                    ArrayList<String>  arrayList = new ArrayList();
                    String link="https://pl.wikipedia.org/wiki/"+country;
                    arrayList.add("111");
                    arrayList.add(country);
                    arrayList.add(link);
                    //list.add(textField.getText());
                    list.add(arrayList);
                    model.addRow(list.toArray());
                }
            }
        });
        basicPanel.add(new JLabel("Pole tekstowe:"));
        basicPanel.add(textField);

        frame.add(basicPanel, BorderLayout.NORTH);


        // === SECTION 5: JTable ===


        Object[][] data = {
                {"Jan", 20, 44},
                {"Anna", 25, 55},
                {"Piotr", 30, 55}
        };


        JTable table = new JTable();
        table.setModel(model);
        JScrollPane tableScroll = new JScrollPane(table);
        frame.add(tableScroll, BorderLayout.CENTER);

        frame.setVisible(true);

    }
}