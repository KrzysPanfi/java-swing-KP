import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

public class Main {

    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }




    private void createAndShowGUI() {
        frame = new JFrame("Swing Tutorial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(8, 8));
        JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        frame.add(basicPanel, BorderLayout.SOUTH);
        DefaultTableModel model = new DefaultTableModel();
        JButton btn=getjButton1(model);
        basicPanel.add(btn);
        model.addColumn("Flaga");
        model.addColumn("Kraj");
        model.addColumn("Wikipedia");
        String country = "Polska";
        Vector<Object> v = new Vector<>();
        Icon flag=new ImageIcon("Flaga_"+country+".png");
        v.add(flag);
        String link = "https://pl.wikipedia.org/wiki/" + country;
        v.add(country);
        v.add(link);
        model.addRow(v);
        frame.add(basicPanel, BorderLayout.SOUTH);
        JTable table = new JTable();
        table.setModel(model);
        JScrollPane tableScroll = new JScrollPane(table);
        table.getColumnModel().getColumn(0).setCellRenderer(new ImageRender());
        table.setRowHeight(100);
        frame.add(tableScroll, BorderLayout.CENTER);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                if (col == 2) {
                    URI uri = null;
                    try {
                        uri = new URI((String) table.getValueAt(row, col));
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (Desktop.isDesktopSupported()) {
                        openWebpage(uri);
                    }
                }
            }
        });

        frame.setVisible(true);

    }

    private static JButton getjButton(JTextField textField, DefaultTableModel model) {
        JButton button = new JButton("Ok");
        button.addActionListener(e -> {
            String country = textField.getText();
            Vector<Object> v = new Vector<>();
            Icon flag = new ImageIcon("Flaga_" + country + ".png");
            v.add(flag);
            String link = "https://pl.wikipedia.org/wiki/" + country;
            v.add(country);
            v.add(link);
            model.addRow(v);
        });
        return button;
    }

    private static JButton getjButton1(DefaultTableModel model) {
        JButton button = new JButton("Dodaj kraj");
        button.addActionListener(e -> {
            JFrame frame1 = new JFrame("Input");
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setSize(500, 200);
            frame1.setLayout(new BorderLayout(8, 8));

            JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            frame1.add(basicPanel, BorderLayout.NORTH);
            JTextField textField = new JTextField(15);
            JButton button1 = getjButton(textField, model);
            JButton button2 = getjButton2(frame1);
            JLabel label = new JLabel("Podaj nazwę kraju:");
            JLabel label1 = new JLabel(new ImageIcon("znak_zapytania.png"));
            basicPanel.add(label1);
            basicPanel.add(label);
            basicPanel.add(textField);
            basicPanel.add(button1);
            basicPanel.add(button2);

            frame1.setVisible(true);
        });
        return button;
    }

    private static JButton getjButton2(JFrame frame) {
        JButton button = new JButton("Cancel");
        button.addActionListener(e -> frame.dispose());
        return button;
    }


    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
 class ImageRender extends DefaultTableCellRenderer{
     @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         String photoName=value.toString();
         ImageIcon imageIcon = new ImageIcon(new ImageIcon(photoName).getImage());
         return new JLabel(imageIcon);
     }
 }

