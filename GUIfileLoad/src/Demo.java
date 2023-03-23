import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Demo extends JFrame {
    private JPanel panelMain;
    private JButton loadButton;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    public File selectedFile;


    String line;
    String parametry = "";

    public Demo() {
        initComponents();
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileRead();
                textArea.append(parametry + "\n");
            }
        });
    }
    private void fileRead() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();

            try (Scanner scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(selectedFile)
                    )
            )) {
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    parametry += line;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initComponents(){
        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);

        menuItem = new JMenuItem("File");
        menu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileRead();
                textArea.append(parametry + "\n");
            }
        });

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        d.setContentPane(d.panelMain);
        d.setSize(500, 500);
        d.setTitle("Load File");
        d.setVisible(true);
        d.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
