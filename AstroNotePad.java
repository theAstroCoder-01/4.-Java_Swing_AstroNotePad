package TextEditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.undo.UndoManager;

public class AstroNotePad extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, helpMenu;
    private JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, exitMenuItem;
    private JMenuItem cutMenuItem, copyMenuItem, pasteMenuItem, selectAllMenuItem, undoMenuItem, redoMenuItem;
    private JMenuItem wordCountMenuItem, aboutMenuItem;
    private File currentFile;
    private UndoManager undoManager;
    private JTextField statusBar;

    public AstroNotePad() {
        setTitle("The Astro Notepad!");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(undoManager);

        statusBar = new JTextField("Ln 1, Col 1");
        statusBar.setEditable(false);
        add(statusBar, BorderLayout.SOUTH);

        textArea.addCaretListener(e -> updateStatusBar());

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        newMenuItem = new JMenuItem("New");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        saveAsMenuItem = new JMenuItem("Save As");
        exitMenuItem = new JMenuItem("Exit");

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        editMenu = new JMenu("Edit");
        undoMenuItem = new JMenuItem("Undo");
        redoMenuItem = new JMenuItem("Redo");
        cutMenuItem = new JMenuItem("Cut");
        copyMenuItem = new JMenuItem("Copy");
        pasteMenuItem = new JMenuItem("Paste");
        selectAllMenuItem = new JMenuItem("Select All");

        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);
        editMenu.addSeparator();
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.addSeparator();
        editMenu.add(selectAllMenuItem);

        helpMenu = new JMenu("Help");
        wordCountMenuItem = new JMenuItem("Word Count");
        aboutMenuItem = new JMenuItem("About");

        helpMenu.add(wordCountMenuItem);
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        saveAsMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        undoMenuItem.addActionListener(this);
        redoMenuItem.addActionListener(this);
        cutMenuItem.addActionListener(this);
        copyMenuItem.addActionListener(this);
        pasteMenuItem.addActionListener(this);
        selectAllMenuItem.addActionListener(this);
        wordCountMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == newMenuItem) {
            newFile();
        } else if (source == openMenuItem) {
            openFile();
        } else if (source == saveMenuItem) {
            saveFile();
        } else if (source == saveAsMenuItem) {
            saveFileAs();
        } else if (source == exitMenuItem) {
            System.exit(0);
        } else if (source == undoMenuItem) {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } else if (source == redoMenuItem) {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        } else if (source == cutMenuItem) {
            textArea.cut();
        } else if (source == copyMenuItem) {
            textArea.copy();
        } else if (source == pasteMenuItem) {
            textArea.paste();
        } else if (source == selectAllMenuItem) {
            textArea.selectAll();
        } else if (source == wordCountMenuItem) {
            showWordCount();
        } else if (source == aboutMenuItem) {
            JOptionPane.showMessageDialog(this, "The Astro Notepad! by theAstroCoder-01 (github.com)", "About The Astro Notepad",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void newFile() {
        currentFile = null;
        textArea.setText("");
        setTitle("The Astro Notepad!");
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                textArea.read(reader, null);
                setTitle("The Astro Notepad! - " + currentFile.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        if (currentFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                textArea.write(writer);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            saveFileAs();
        }
    }

    private void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            if (!currentFile.getName().contains(".")) {
                currentFile = new File(currentFile + ".txt");
            }
            saveFile();
            setTitle("The Astro Notepad! - " + currentFile.getName());
        }
    }

    private void showWordCount() {
        String text = textArea.getText();
        int wordCount = text.split("\\s+").length;
        JOptionPane.showMessageDialog(this, "Word Count: " + wordCount, "Word Count", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateStatusBar() {
        int line = 1, col = 1;
        try {
            int caretPos = textArea.getCaretPosition();
            line = textArea.getLineOfOffset(caretPos) + 1;
            col = caretPos - textArea.getLineStartOffset(line - 1) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        statusBar.setText("Ln " + line + ", Col " + col);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AstroNotePad notepad = new AstroNotePad();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - notepad.getWidth()) / 2;
            int y = (screenSize.height - notepad.getHeight()) / 2;
            notepad.setLocation(x, y);

            notepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            notepad.setResizable(false);
            notepad.setVisible(true);
        });
    }
}
