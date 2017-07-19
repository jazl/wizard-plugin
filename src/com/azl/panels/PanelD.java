package com.azl.panels;

import javax.swing.*;
import java.awt.*;

public class PanelD extends JPanel {
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Create the panel.
     */
    public PanelD() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JLabel lblFirstName = new JLabel("FIrst Name");
        GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
        gbc_lblFirstName.insets = new Insets(0, 0, 0, 5);
        gbc_lblFirstName.anchor = GridBagConstraints.EAST;
        gbc_lblFirstName.gridx = 0;
        gbc_lblFirstName.gridy = 0;
        panel.add(lblFirstName, gbc_lblFirstName);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        panel.add(textField, gbc_textField);
        textField.setColumns(10);

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;
        add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{192, 0, 66, 0};
        gbl_panel_1.rowHeights = new int[]{16, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);

        JLabel lblLastName = new JLabel("Last Name");
        GridBagConstraints gbc_lblLastName = new GridBagConstraints();
        gbc_lblLastName.anchor = GridBagConstraints.WEST;
        gbc_lblLastName.fill = GridBagConstraints.VERTICAL;
        gbc_lblLastName.insets = new Insets(0, 0, 0, 5);
        gbc_lblLastName.gridx = 0;
        gbc_lblLastName.gridy = 0;
        panel_1.add(lblLastName, gbc_lblLastName);

        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.insets = new Insets(0, 0, 0, 5);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 1;
        gbc_textField_1.gridy = 0;
        panel_1.add(textField_1, gbc_textField_1);
        textField_1.setColumns(10);

        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 2;
        add(panel_2, gbc_panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{0, 0};
        gbl_panel_2.rowHeights = new int[]{0, 0, 0};
        gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);

        JLabel lblNewLabel = new JLabel("Description");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel_2.add(lblNewLabel, gbc_lblNewLabel);

        JTextPane textPane = new JTextPane();
        GridBagConstraints gbc_textPane = new GridBagConstraints();
        gbc_textPane.fill = GridBagConstraints.BOTH;
        gbc_textPane.gridx = 0;
        gbc_textPane.gridy = 1;
        panel_2.add(textPane, gbc_textPane);

    }

}
