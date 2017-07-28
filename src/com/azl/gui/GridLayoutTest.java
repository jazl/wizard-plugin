package com.azl.gui;

import com.azl.custom.ContextMenuMouseListener;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.table.TableView;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class GridLayoutTest extends JFrame {

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

    public GridLayoutTest() {

        table = new JBTable();

        final GridBagLayout mgr = new GridBagLayout();

        setLayout(mgr);
        initHeader();
        initSearch();
        initTable();
        initSubscription();
        initTechStack();
        initAdvancedSettings();
    }

    private void initHeader() {
        final String title = "Please select a release to upload to Fortify on Demand";
        final FodUploadHeader header = new FodUploadHeader(title, "");
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0, 0, 5, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        this.add(header, constraints);
    }

    private void initSearch() {
        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 1;
        panelConstraints.weightx = 0.0;
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;
        panelConstraints.insets = new Insets(0,5,0,0);
        add(new JLabel("Search:"), panelConstraints);

        panelConstraints.gridx = 1;
        panelConstraints.gridy = 1;
        panelConstraints.weightx = 2000000;
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;
        txtSearch = new JTextField(100);
        txtSearch.addMouseListener(new ContextMenuMouseListener());
        add(txtSearch, panelConstraints);

        panelConstraints.gridx = 2;
        panelConstraints.gridy = 1;
        panelConstraints.weightx = 0;
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;
        btnSearch = new JButton();
        btnSearch.setIcon(AllIcons.Actions.Find);
        //btnSearch.setIcon(AllIcons.Actions.Find);
        add(btnSearch, panelConstraints);

        panelConstraints.gridx = 3;
        panelConstraints.gridy = 1;
        panelConstraints.weightx = 0;
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;
        btnRefresh = new JButton();
        btnRefresh.setIcon(AllIcons.Actions.Refresh);
        add(btnRefresh, panelConstraints);

        panelConstraints.gridx = 4;
        panelConstraints.gridy = 1;
        panelConstraints.weightx = 0;
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;
        btnLogout = new JButton("Logout");
        btnLogout.setIcon(AllIcons.Actions.Prevfile);
        //btnLogout.setIcon(new ImageIcon(ReleasesListPanel.class.getResource("/icons/logout.gif")));
        btnLogout.setToolTipText("Change API Root URL or tenant");
        add(btnLogout, panelConstraints);

    }

    private void initTable() {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.updateUI();
        final JBScrollPane scrollPane = new JBScrollPane(table);
//        scrollPane.addMouseWheelListener(new MouseWheelListener() {
//            @Override
//            public void mouseWheelMoved(MouseWheelEvent e) {
//                int value = scrollPane.getVerticalScrollBar().getValue();
//                int max= scrollPane.getVerticalScrollBar().getMaximum();
//                if (value + scrollPane.getVerticalScrollBar().getHeight() >= scrollPane.getVerticalScrollBar().getMaximum()) {
//                    model.downloadReleases();
//                }
//            }
//        });
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weighty = 100;
        constraints.gridwidth = 5;
        constraints.insets = new Insets(10, 0, 10, 0);
        add(scrollPane, constraints);
    }

    private void initSubscription() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 3;
        constraints.gridwidth = 5;
        constraints.anchor = GridBagConstraints.EAST;
        units = new JLabel("0 Unit(s) available", SwingConstants.RIGHT);
        add(units, constraints);

        constraints = new GridBagConstraints();
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(5, 5, 0, 5);
        add(new JLabel("Assesment Type"), constraints);

        // tech stack combo
        constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.gridy = 4;
        constraints.gridx = 1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 0, 0, 5);
        assesmentTypeComboBox = new ComboBox<>();
        add(assesmentTypeComboBox, constraints);
    }

    private void initTechStack() {
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new LineBorder(Color.lightGray));

        GridBagConstraints constraints;

        // autodetect button
        constraints = new GridBagConstraints();
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JButton("Autodetect"), constraints);

        // tech stack label
        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Technology Stack"), constraints);

        // tech stack combo
        constraints = new GridBagConstraints();
        techStackCombo = new ComboBox<String>();
        constraints.weightx = 1;
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 0, 0, 5);
        panel.add(techStackCombo, constraints);

        // language level label
        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Language Level"), constraints);

        // language level combo
        constraints = new GridBagConstraints();
        languageLevelCombo = new ComboBox<>();
        constraints.weightx = 1;
        constraints.gridy = 2;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 0, 0, 5);
        panel.add(languageLevelCombo, constraints);

        // open source analysis checkbox
        constraints = new GridBagConstraints();
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(5, 0, 0, 0);
        useSonatypeCombo = new JCheckBox("Open Source Component Analysis");
        panel.add(useSonatypeCombo, constraints);

        // add panel to dialog
        constraints = new GridBagConstraints();
        constraints.gridy = 5;
        constraints.gridwidth = 5;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 0, 5, 0);
        add(panel, constraints);
    }

    private void initAdvancedSettings() {
        final JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints;

        // scan preference label
        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Scan Preference"), constraints);

        // scan preference combo
        constraints = new GridBagConstraints();
        scanPreferenceCombo = new ComboBox<>();
        constraints.weightx = 1;
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 0, 0, 5);
        panel.add(scanPreferenceCombo, constraints);

        // audit preference label
        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("AuditPreference"), constraints);

        // audit preferences combo
        constraints = new GridBagConstraints();
        auditPreferenceCombo = new ComboBox<>();
        constraints.weightx = 1;
        constraints.gridy = 2;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 0, 0, 5);
        panel.add(auditPreferenceCombo, constraints);

        // third party libraries checkbox
        constraints = new GridBagConstraints();
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(5, 0, 0, 0);
        useThirdParty = new JCheckBox("Include third-party libraries for security assessment");
        panel.add(useThirdParty, constraints);

        // add panel to dialog
        constraints = new GridBagConstraints();
        constraints.gridy = 6;
        constraints.gridwidth = 5;
        constraints.fill = GridBagConstraints.BOTH;
        add(panel, constraints);

        //addAuditModes(auditPreferenceCombo);
        //addScanModes(scanPreferenceCombo);
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

        GridLayoutTest mainFrame = new GridLayoutTest();
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(600,500);
        mainFrame.setVisible(true);
    }
}