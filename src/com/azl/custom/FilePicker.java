package com.azl.custom;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class FilePicker extends JPanel {

    private String dialogTitle;         // title of file/open dialog
    private List<String> fileHistory;   // TODO: maybe Paths?
    private FileNameExtensionFilter fileNameExtensionFilter;

    private JComboBox cboFileLocation;
    private JButton btnBrowse;
    private JFileChooser fileChooser;

    public FilePicker() {
        this("Select a file");
    }

    public FilePicker(String title) {
        createContent();
        setDialogTitle(title);
    }

    public void setDialogTitle(String title) {
        dialogTitle = title;
    }

    public void setEnabled(boolean enabled){
        cboFileLocation.setEnabled(enabled);
        btnBrowse.setEnabled(enabled);
    }

    public void setFileHistory(List<String> files){
        // TODO
    }

    public void setFileNameFilters() {
        // TODO
    }

    public void clearFileHistory(){
        cboFileLocation.removeAllItems();
    }

    public void saveFileToHistory() {
        cboFileLocation.addItem(cboFileLocation.getSelectedItem());
    }

    private void createContent() {

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        cboFileLocation = new JComboBox();
        cboFileLocation.setEditable(true);

        GridBagConstraints gbc_cboFileLocation = new GridBagConstraints();
        gbc_cboFileLocation.insets = new Insets(0, 0, 0, 5);
        gbc_cboFileLocation.fill = GridBagConstraints.HORIZONTAL;
        gbc_cboFileLocation.gridx = 0;
        gbc_cboFileLocation.gridy = 0;
        add(cboFileLocation, gbc_cboFileLocation);

        btnBrowse = new JButton("Browse...");
        GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
        gbc_btnBrowse.gridx = 1;
        gbc_btnBrowse.gridy = 0;
        add(btnBrowse, gbc_btnBrowse);

        setBrowseActions(btnBrowse);
        setFileChooserOptions();
    }

    private void setBrowseActions(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileChooser.showOpenDialog(FilePicker.this) == JFileChooser.APPROVE_OPTION){
                    cboFileLocation.setSelectedItem(fileChooser.getSelectedFile());
                    saveFileToHistory();
                };
            }
        });
    }

    private void setFileChooserOptions() {
        fileChooser = new JFileChooser();
        fileNameExtensionFilter = new FileNameExtensionFilter("Zip file","zip");
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileNameExtensionFilter);
    }
}
