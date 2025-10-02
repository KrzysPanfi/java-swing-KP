import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

public class Main {

    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    private static JButton getjButton(JTextField textField, DefaultTableModel model) {
        JButton button = new JButton("Dodaj Panstwo");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String country = textField.getText();
                Vector<Object> v = new Vector<>();
                Icon flag=new ImageIcon("Flaga_"+country+".png");
                v.add(flag);
                String link = "https://pl.wikipedia.org/wiki/" + country;
                v.add(country);
                v.add(link);
                model.addRow(v);
            }
        });
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


    private void createAndShowGUI() {
        frame = new JFrame("Swing Tutorial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(8, 8));

        // === SECTION 1: Podstawowe przyciski i pola tekstowe ===
        JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // JButton z ActionListener


        frame.add(basicPanel, BorderLayout.SOUTH);
        JTextField textField = new JTextField(15);
        DefaultTableModel model = new DefaultTableModel();

        ArrayList<ArrayList<String>> list = new ArrayList<>();
        JButton button1 = getjButton(textField, model);
        basicPanel.add(button1);
        model.addColumn("Flaga");
        model.addColumn("Kraj");
        model.addColumn("Wiki");
        basicPanel.add(new JLabel("Pole tekstowe:"));
        basicPanel.add(textField);
        String country = "Polska";
        Vector<Object> v = new Vector<>();
        Icon flag=new ImageIcon("Flaga_"+country+".png");
        v.add(flag);
        String link = "https://pl.wikipedia.org/wiki/" + country;
        v.add(country);
        v.add(link);
        model.addRow(v);
        frame.add(basicPanel, BorderLayout.NORTH);



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

}
 class ImageRender extends DefaultTableCellRenderer{
     @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         String photoName=value.toString();
         ImageIcon imageIcon = new ImageIcon(new ImageIcon(photoName).getImage());
         return new JLabel(imageIcon);
     }
 }

