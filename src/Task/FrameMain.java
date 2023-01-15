package Task;

import util.ArrayUtils;
import util.SwingUtils;
import util.JTableUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class FrameMain extends JFrame {
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonLoadInputFromFile;
    private JButton buttonRandomInput;
    private JButton buttonSort;
    private JButton buttonSaveOutputIntoFile;
    private JPanel panelMain;
    private JTable tableInput2;
    private JButton buttonLoadInputFromFile2;
    private JButton buttonRandomInput2;
    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("Task 9");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, false, true, false, true);
        JTableUtils.initJTableForArray(tableInput2, 40, false, true, false, true);
        JTableUtils.initJTableForArray(tableOutput, 40, false, true, false, false);
        tableOutput.setEnabled(false);
        tableInput.setRowHeight(25);
        tableInput2.setRowHeight(25);
        tableOutput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");


        buttonLoadInputFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] array = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput, array);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonLoadInputFromFile2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] array = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput2, array);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonRandomInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] array = ArrayUtils.createRandomIntArray(tableInput.getColumnCount(), 0, 100);
                    JTableUtils.writeArrayToJTable(tableInput, array);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonRandomInput2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] array = ArrayUtils.createRandomIntArray(tableInput2.getColumnCount(), 0, 100);
                    JTableUtils.writeArrayToJTable(tableInput2, array);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSaveOutputIntoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] array = JTableUtils.readIntArrayFromJTable(tableOutput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, array, null);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }

            }
        });
        buttonSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] arrayList = JTableUtils.readIntArrayFromJTable(tableInput);
                    int[] arrayList2 = JTableUtils.readIntArrayFromJTable(tableInput2);
                    List<Integer> list = Logic.createNewList(ArrayUtils.toList(arrayList), ArrayUtils.toList(arrayList2));
                    int[] answer = list.stream().mapToInt(i -> i).toArray();
                    JTableUtils.writeArrayToJTable(tableOutput, answer);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}