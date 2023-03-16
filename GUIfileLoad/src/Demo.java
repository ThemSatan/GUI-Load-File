import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Demo extends JFrame {
    private JPanel panelMain;
    private JButton loadButton;
    private JTextArea textArea;
    public File selectedFile;

    String line;
    String parametry = "";

    private void FileReader() {
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

    public Demo() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileReader();
                textArea.append(parametry + "\n");
            }
        });
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
