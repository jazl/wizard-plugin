package com.azl.gui;

import com.azl.custom.ContextMenuMouseListener;
import com.azl.custom.JLinkButton;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class JLinkButtonLayoutTest extends JFrame {

    private JTable table;
    private JComboBox<String> techStackCombo;
    private JComboBox<String> assesmentTypeComboBox;
    private JComboBox<String> languageLevelCombo;
    private JComboBox<String> scanPreferenceCombo;
    private JComboBox<String> auditPreferenceCombo;
    private JLabel units;
    private JCheckBox useSonatypeCombo;
    private JCheckBox useThirdParty;
    private JButton btnLogout;
    private JButton btnRefresh;
    private JButton btnSearch;
    private JTextField txtSearch;

    public JLinkButtonLayoutTest() {
        table = new JBTable();

        final GridBagLayout mgr = new GridBagLayout();
        mgr.rowHeights = new int[]{10,10,10,10};
        mgr.columnWidths = new int[]{250,250};
        setLayout(mgr);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton b1 = new JButton("*** BUTTON ***");
        add(b1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton b2 = new JButton("THIS IS A BUTTON 2 WITH LONG TEXT");
        add(b2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLinkButton l1 = new JLinkButton("BUTTON 3");
        //l1.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        l1.setHorizontalAlignment(JLabel.RIGHT);
        add(l1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLinkButton l2 = new JLinkButton("BUTTON 4");
        l2.setHorizontalAlignment(JButton.RIGHT);
        add(l2, gbc);

    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JLinkButtonLayoutTest mainFrame = new JLinkButtonLayoutTest();
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(600,250);
        mainFrame.setTitle("Fun with JLinkButton");
        mainFrame.setVisible(true);
    }
}