package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = new File("src/editor/FileManager.java");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
                textEditor.currentFile = file;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Error reading file: " + e.getMessage());
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = new File("src/editor/FileManager.java");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                  writer.write(textArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(textEditor, "Eror saving file: " + e.getMessage());
                }
            }
        }
        else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Error saving file: " + e.getMessage());
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        int result = JOptionPane.showConfirmDialog(textEditor, "If you creat new file unsaved changes will be lost.\nAre you sure?" ,
                "New File", + JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            textArea.setText("");
            textEditor.currentFile = null;
        }
    }
}
