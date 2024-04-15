import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecursiveListerFrame extends JFrame {
    private JTextArea resultTA;

    public RecursiveListerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Recursive File Lister");

        JButton startBtn = new JButton("Start");
        JButton quitBtn = new JButton("Quit");
        resultTA = new JTextArea();
        JScrollPane scrollPn = new JScrollPane(resultTA);
        JLabel titleLbl = new JLabel("Recursive File Lister");

        JPanel btnPnl = new JPanel(new FlowLayout());
        btnPnl.add(startBtn);
        btnPnl.add(quitBtn);

        add(titleLbl, BorderLayout.NORTH);
        add(btnPnl, BorderLayout.SOUTH);
        add(scrollPn, BorderLayout.CENTER);

        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseDirectory();
            }
        });

        quitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void chooseDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Choose a Directory");
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            listFiles(selectedDirectory);
        }
    }

    private void listFilesRecursively(File dir) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesRecursively(file);
                } else {
                    resultTA.append(file.getAbsolutePath() + "\n");
                }
            }
        }
    }

    private void listFiles(File dir) {
        resultTA.setText("");
        listFilesRecursively(dir);
    }
}